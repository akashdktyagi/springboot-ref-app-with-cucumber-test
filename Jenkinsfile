pipeline {
    agent any
    stages {
        stage('sonar') {
            steps {
                sh 'mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=akashdktyagi_springboot-ref-app-with-cucumber-test'
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