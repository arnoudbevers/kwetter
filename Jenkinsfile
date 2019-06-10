pipeline {
	agent any
	tools {
			maven 'maven'
			jdk 'jdk'
	}
	stages {
			stage('Checkout SCM'){
					steps {
							checkout scm
					}
			}
			stage('Package backend') {
					steps {
							sh "mvn -f ${WORKSPACE}/kwetter-backend/ -B -DskipTests clean package"
					}
			}
			
			stage('Backend test') {
					steps {
						sh "mvn -f ${WORKSPACE}/kwetter-backend/ clean jacoco:prepare-agent install jacoco:report"
						sh "mvn -f ${WORKSPACE}/kwetter-backend/ test"
					}
			}
			stage('Build Docker images') {
					steps {
							docker.build("arnoudbevers/kwetter-backend:latest", "kwetter-backend/Dockerfile")	
							docker.build("arnoudbevers/kwetter-frontend:latest", "kwetter-frontend/Dockerfile")	
							docker.build("arnoudbevers/kwetter-websockets:latest", "kwetter-websockets/Dockerfile")
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
			stage('Publish Docker') {
					when {
							branch 'master'
					}
					steps {
						docker.withRegistry("https://cloud.docker.com/repository/docker/abevers/personal", "c64b17f6-0e70-4328-8cb3-741a9fd359d1") {
							echo 'Building and pushing backend..'
							def backendImage = docker.build('arnoudbevers/kwetter-backend:latest', 'kwetter-backend/Dockerfile')
							backendImage.push()
							echo 'Building and pushing frontend..'
							def frontendImage = docker.build('arnoudbevers/kwetter-frontend:latest', 'kwetter-frontend/Dockerfile')
							frontendImage.push()
							echo 'Building and pushing websockets..'
							def websocketsImage = docker.build('arnoudbevers/kwetter-websockets:latest', 'kwetter-websockets/Dockerfile')
							websocketsImage.push()
						}
					}
			}
	}
	post {
		always {
			emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}",
					recipientProviders: [[$class: 'DevelopersRecipientProvider']],
					subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
		}
	}
}
