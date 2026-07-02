pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'backend-app'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }

        stage('Docker Run') {
            steps {
                sh "docker stop ${DOCKER_IMAGE} || true"
                sh "docker rm ${DOCKER_IMAGE} || true"
                sh "docker run -d -p 8088:8088 --name ${DOCKER_IMAGE} ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }
    }

    post {
        failure {
            echo 'Backend pipeline failed'
        }
        success {
            echo 'Backend deployed successfully'
        }
    }
}
