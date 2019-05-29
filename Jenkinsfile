pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'jdk'
    }
    stages {
        stage('Initialize'){
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
                emailext (
                  subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                  body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                  recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )
            }
        }
        stage('Build') {
            steps {
                sh "mvn -f ${WORKSPACE}/kwetter-backend/ -B -DskipTests clean package"
            }
        }
        stage('SonarQube') {
            environment {
                scannerHome = tool 'SonarQubeScanner'
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "${scannerHome}/bin/sonar-scanner"
                }
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Test') {
            steps {
                sh "mvn -f ${WORKSPACE}/kwetter-backend/ test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
