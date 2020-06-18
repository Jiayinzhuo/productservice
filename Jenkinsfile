pipeline {

  environment {
    PROJECT = "jenkins-demo-278619"
    APP_NAME = "productservice"
    FE_SVC_NAME = "${APP_NAME}"
    CLUSTER = "demo"
    CLUSTER_ZONE = "us-east1-d"
    IMAGE_TAG = "gcr.io/${PROJECT}/${APP_NAME}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
    JENKINS_CRED = "${PROJECT}"
    BUILD_CONTEXT = "build-context-${BUILD_NUMBER}.tar.gz"
  }

  agent {
    kubernetes {
      label 'sample-app'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: maven
    image: maven:3.3.9-jdk-8-alpine
    command: 
    - cat
    tty: true
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
  }
  stages {
    stage('Build and Test') {
      steps {
      	container('maven') {
			//run tests
			sh "mvn clean verify"

			//build
			sh "mvn clean package"
						
			//bundle the generated artifact
			//sh "cp target/${APP_NAME}-*.jar $APP_JAR"
			
			// archive the build context for kaniko 
			//sh "tar --exclude='./.git' -zcvf /tmp/$BUILD_CONTEXT ."
			//sh "mv /tmp/$BUILD_CONTEXT ."
      	}
      }
    }           
    stage('Build and push image with Container Builder') {
      steps {
        container('gcloud') {
          sh "PYTHONUNBUFFERED=1 gcloud builds submit -t ${IMAGE_TAG} ."
        }
      }
    }
    stage('Deploy') {
      when { branch 'master' }
      steps{
        container('kubectl') {
          // Change deployed image to the one we just built
          sh("sed -i.bak 's#gcr.io/jenkins-demo-278619/productservice:1.0.0#${IMAGE_TAG}#' ./k8s/*.yaml")
          step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT, clusterName: env.CLUSTER, zone: env.CLUSTER_ZONE, manifestPattern: 'k8s', credentialsId: env.JENKINS_CRED, verifyDeployments: true])
          sh("echo http://`kubectl --namespace=production get service/${FE_SVC_NAME} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${FE_SVC_NAME}")
          echo 'To access your environment run `kubectl proxy`'
          echo "Then access your service via http://localhost:8001/api/v1/proxy/namespaces/${env.BRANCH_NAME}/services/${FE_SVC_NAME}:80/"
        }
      }
    }
  }
}
