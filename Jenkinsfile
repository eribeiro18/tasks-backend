pipeline {
	agent any
	stages {
		stage ('Build Backend') {
			steps {
				sh 'mvn clean package -DskipTests=true'
			}
			steps {
				sh 'mvn test'
			}
		}
	}
}