pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building and testing if changes work..'
            }
        }
        stage('SonarQube analysis') {
            // requires SonarQube Scanner 2.8+
            def scannerHome = tool 'SonarQube Scanner 2.8';
            withSonarQubeEnv('My SonarQube Server') {
              sh "${scannerHome}/bin/sonar-scanner"
          }
        } 
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
