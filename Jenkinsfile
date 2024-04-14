pipeline {
	agent any
	stages {
		stage ('Build Backend') {
			steps {
				sh 'mvn clean package -DskipTests=true'
			}
		}
		stage ('JUnit Tests') {
			steps {
				sh 'mvn test'
			}
		}
		stage ('Sonar Analysis') {
			environment {
				scannerHome = tool 'SONAR_SCANER'   
			}
			steps {
				withSonarQubeEnv('SONAR_LOCAL'){
					sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_14247a652605577956712dc399c6ce2be2aaa03d -Dsonar.java.jdkHome=JAVA_LOCAL -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java,**Docker**,**docker**"    
				}				
			}
		}
		stage ('Quality Gate') {
			steps {
				sleep(8)
			    timeout(time: 1, unit: 'MINUTES'){
			        waitForQualityGate abortPipeline: true
			    }
			}
		}
		stage ('Deploy Backend') {
			steps {
				deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
			}
		}
		stage ('API Test') {
			steps {
				dir('api-test'){
					git 'https://github.com/eribeiro18/tasks-api-test'
					sh 'mvn test'
				}
			}
		}
		stage ('Deploy Frontend') {
			steps {
				dir('frontend'){
					git 'https://github.com/eribeiro18/tasks-frontend'
					sh 'mvn clean package'
					deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
				}				
			}
		}
		stage ('Functional Text') {
			steps {
				dir('function-test'){
					git 'https://github.com/eribeiro18/tasks-functional-tests'
					sh 'mvn test'
				}				
			}
		}
		stage ('Deploy Prod') {
			steps {
				dir('function-test'){
					sh 'docker-compose down'
					sh 'docker-compose build'
					sh 'docker-compose up -d'
				}				
			}
		}
		stage ('Health Check') {
			steps {
				sleep(8)
				dir('function-test'){
					sh 'mvn verify -Dskip.surefire.tests'
				}				
			}
		}
	}
}