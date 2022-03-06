pipeline {
    agent any
    stages {
        stage('sonar') {
            steps {
                withEnv(['SONAR_TOKEN=0ab2cf1faf9fb442a7fa818f57cf552e1d3b9649']) {
                     sh 'mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=akashdktyagi_springboot-ref-app-with-cucumber-test'
                }

            }
        }

        stage('Build Create and Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'p', usernameVariable: 'u')]) {
                   sh "mvn -B clean install -Djib.to.auth.username=$u -Djib.to.auth.password=$p"
                }

            }
        }


    }
}