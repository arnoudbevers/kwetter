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
						dir('kwetter-backend') {
							sh 'docker build -t arnoudbevers/kwetter-backend:latest .'
						}
						dir('kwetter-frontend'){
							sh 'docker build -t arnoudbevers/kwetter-frontend:latest .'
						}
						dir('kwetter-websockets') {
							sh 'docker build -t arnoudbevers/kwetter-websockets:latest .'
						}
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
						script {
							docker.withRegistry([url: "https://hub.docker.com" , credentialsId: "c64b17f6-0e70-4328-8cb3-741a9fd359d1"]) {
								echo 'Pushing backend..'
								sh 'docker push arnoudbevers/kwetter-backend:latest'
								echo 'Tagging and pushing frontend..'
								sh 'docker push arnoudbevers/kwetter-frontend:latest'
								echo 'Tagging and pushing websockets..'
								sh 'docker push arnoudbevers/kwetter-websockets:latest'
							}
						}
				
					}
			}
	}
	post {
		always {
			emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
					recipientProviders: [[$class: 'DevelopersRecipientProvider']],
					subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
		}
	}
}
