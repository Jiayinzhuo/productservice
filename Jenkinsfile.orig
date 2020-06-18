node {
  stage('SCM Checkout uses GitHub') {
    git 'https://github.com/Jiayinzhuo/productservice.git'
  }

  stage('Tool Installation') {
    def mvnHome = tool name:'M3', type: 'maven'
    def mvnCMD = "${mvnHome}/bin/mvn"
    sh "${mvnCMD} clean test"
  }

  stage('Compile and Package Uses Maven') {
    def mvnHome = tool name:'M3', type: 'maven'
    def mvnCMD = "${mvnHome}/bin/mvn"
    sh "${mvnCMD} clean package"
  }

  stage('Build Docker Images') {
    sh 'docker-compose build'
  }

  stage('Login to Docker Hub') {
    withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerhubpwd')]) {
      sh 'docker login -u jiayinzhuo -p ${dockerhubpwd}'
    }
  }

  stage('Push Images to Docker Hub') {
  	sh 'echo "Push to Docker Hub"' 
    sh 'docker-compose push'
  }

  stage('Deploy to Kubernetes Cluster') {
  	sh 'echo "Deploy to Kubernetes"'
    sh 'kubectl apply -R -f ./k8s'
  }
}
