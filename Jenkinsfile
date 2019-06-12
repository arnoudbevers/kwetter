	pipeline {
		agent any
		tools {
				maven "maven"
				jdk "jdk"
		}
		stages {
				stage("Checkout SCM") 	{
						steps {
								checkout scm
						}
				}
				stage("Package backend") {
						steps {
								sh "mvn -f ${WORKSPACE}/kwetter-backend/ -B -DskipTests clean package"
						}
				}
				
				stage("Backend test") {
						steps {
							sh "mvn -f ${WORKSPACE}/kwetter-backend/ clean jacoco:prepare-agent install jacoco:report"
							sh "mvn -f ${WORKSPACE}/kwetter-backend/ test"
						}
				}
				stage("Build Docker images") {
						steps {
							dir("kwetter-backend") {
								sh "docker build -t abevers/kwetter-backend:${BUILD_ID} ."
								sh "docker build -t abevers/kwetter-backend:latest ."
							}
							dir("kwetter-frontend") {
								sh "docker build -t abevers/kwetter-frontend:${BUILD_ID} ."
								sh "docker build -t abevers/kwetter-frontend:latest ."
							}
							dir("kwetter-websockets") {
								sh "docker build -t abevers/kwetter-websockets:${BUILD_ID} ."
								sh "docker build -t abevers/kwetter-websockets:latest ."
							}
						}
				}
				stage("SonarQube") {
						environment {
								scannerHome = tool "SonarQubeScanner"
						}
						steps {
								withSonarQubeEnv("SonarQube") {
										sh "${scannerHome}/bin/sonar-scanner"
								}
								timeout(time: 5, unit: "MINUTES") {
										waitForQualityGate abortPipeline: true
								}
						}
				}
        stage("Publish build version to docker") {
          steps {
            script {
              docker.withRegistry("", "c64b17f6-0e70-4328-8cb3-741a9fd359d1") {
              echo "Pushing build version ofbackend.."
              sh "docker push abevers/kwetter-backend:${BUILD_ID}"
              echo "Pushing build version of frontend.."
              sh "docker push abevers/kwetter-frontend:${BUILD_ID}"
              echo "Pushing build version of websockets.."
              sh "docker push abevers/kwetter-websockets:${BUILD_ID}"
            }
          }
        }
				stage("Publish latest version to docker") {
						when {
								branch "develop"
						}
						steps {
							script {
								docker.withRegistry("", "c64b17f6-0e70-4328-8cb3-741a9fd359d1") {
									echo "Pushing latest version of backend.."
									sh "docker push abevers/kwetter-backend:latest"
									echo "Pushing latest version of frontend.."
									sh "docker push abevers/kwetter-frontend:latest"
									echo "Pushing latest version of websockets.."
									sh "docker push abevers/kwetter-websockets:latest"
								}
							}
						}
				}
		}
		post {
			success {
				emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
						recipientProviders: [[$class: "DevelopersRecipientProvider"]],
						subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
			}
      failure {
				emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
						recipientProviders: [[$class: "CulpritsRecipientProvider"]],
						subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
			}
      always {
        archiveArtifacts '*'
      }
		}
	}
