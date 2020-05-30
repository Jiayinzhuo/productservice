pipeline {
    agent any
    tools { 
        maven 'M3' 
        jdk 'jdk8'
    }
    environment {
        DISABLE_AUTH = 'true'
    }
    stages {
        stage ('Tool Installation') {
            steps {
 				sh 'echo "Jenkins, Github, K8S Demo"'
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Compile and Package Uses Maven') {
            steps {
                sh 'mvn --version'
                sh 'printenv'
                sh 'mvn clean package'
            }
        }
        
        stage ('Testing Stage Uses JUnit') {
            steps {
                sh 'mvn test'
            }
        }

        stage ('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }

        stage ('Login to Docker Hub') {
            steps {
                withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerhubpwd')]) {
                	sh 'docker login -u jiayinzhuo -p ${dockerhubpwd}'
                }
            }
        }

        stage ('Push Images to Docker Hub') {
            steps {
                sh 'echo "Push to Docker Hub"'
                sh 'docker-compose push'
            }
        }

        stage ('Deploy to Kubernetes Cluster') {
            steps {
                sh 'echo "Deploy to Kubernetes"'
                sh 'kubectl apply -R -f ./k8s'
            }
        }
    }
}

