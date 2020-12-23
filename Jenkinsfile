pipeline {

    environment {
        REGISTRY = "oriolbellet/match-player"
        REGISTRY_CREDENTIAL = 'dockerhub'
        DOCKER_IMAGE = ''
        PROJECT_ID = 'king-oriolbellet-sandbox'
        CLUSTER_NAME = 'multi-cluster'
        LOCATION = 'us-central1-c'
        CREDENTIALS_ID = 'football'
        GIT_SHA = sh(returnStdout: true, script: 'git rev-parse HEAD').trim().take(6)
    }

    agent any

    tools {
        maven 'jenkins-maven'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker') {
            when { branch 'master' }
            steps{
                script {
                    DOCKER_IMAGE = docker.build REGISTRY + ":$GIT_SHA"
                }
            }
        }

        stage('Release to Docker Hub') {
            when { branch 'master' }
            steps {
                script {
                    docker.withRegistry( '', REGISTRY_CREDENTIAL ) {
                        DOCKER_IMAGE.push()
                        DOCKER_IMAGE.push('latest')
                    }
                }
                sh "docker rmi $REGISTRY:$GIT_SHA"
            }
        }

        stage('Deploy to GKE') {
            when { branch 'master' }
            steps{
                sh "sed -i 's/football:latest/football:$GIT_SHA/g' deployment.yml"
                step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'deployment.yml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
            }
        }

    }
    post {
        always {
           cleanWs()
        }
    }

}
