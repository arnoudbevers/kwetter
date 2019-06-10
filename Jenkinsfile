pipeline {
	agent any
	tools {
			maven 'maven'
			jdk 'jdk'
	}
	stages {
			stage('Checkout SCM') 	{
					steps {
							checkout scm
					}
			}
			stage('Package backend') {
					steps {
							sh 'mvn -f ${WORKSPACE}/kwetter-backend/ -B -DskipTests clean package'
					}
			}
			
			stage('Backend test') {
					steps {
						sh 'mvn -f ${WORKSPACE}/kwetter-backend/ clean jacoco:prepare-agent install jacoco:report'
						sh 'mvn -f ${WORKSPACE}/kwetter-backend/ test'
					}
			}
			stage('Build Docker images') {
					steps {
						dir('kwetter-backend') {
							sh 'docker build -t arnoudbevers/kwetter-backend:${BUILD_ID} .'
						}
						dir('kwetter-frontend') {
							sh 'docker build -t arnoudbevers/kwetter-frontend:${BUILD_ID} .'
						}
						dir('kwetter-websockets') {
							sh 'docker build -t arnoudbevers/kwetter-websockets:${BUILD_ID} .'
						}
					}
			}
			stage('SonarQube') {
					environment {
							scannerHome = tool 'SonarQubeScanner'
					}
					steps {
							withSonarQubeEnv('SonarQube') {
									sh '${scannerHome}/bin/sonar-scanner'
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
							docker.withRegistry('https://hub.docker.com', 'c64b17f6-0e70-4328-8cb3-741a9fd359d1') {
								echo 'Pushing backend..'
								sh 'docker push arnoudbevers/kwetter-backend:${BUILD_ID}'
								sh 'docker push arnoudbevers/kwetter-backend:latest'
								echo 'Pushing frontend..'
								sh 'docker push arnoudbevers/kwetter-frontend:${BUILD_ID}'
								sh 'docker push arnoudbevers/kwetter-frontend:latest'
								echo 'Pushing and pushing websockets..'
								sh 'docker push arnoudbevers/kwetter-websockets:${BUILD_ID}'
								sh 'docker push arnoudbevers/kwetter-websockets:latest'
							}
					}
			}
	}
	post {
		always {
			emailext body: '${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}',
					recipientProviders: [[$class: 'DevelopersRecipientProvider']],
					subject: 'Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}'
		}
	}
}
