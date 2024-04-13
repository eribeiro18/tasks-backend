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
					sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_14247a652605577956712dc399c6ce2be2aaa03d -Dsonar.java.jdkHome=JAVA_LOCAL -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"    
				}				
			}
		}
		stage ('Quality Gate') {
			steps {
				sleep(5)
			    timeout(time: 1, unit: 'MINUTES'){
			        waitForQualityGate abortPipeline: true
			    }
			}
		}
	}
}