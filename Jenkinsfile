pipeline {
	agent any
	stages {
		stage ('Build Backend') {
			steps {
				sh 'mvn clean package -DskipTests=true'
			}
		}
	}
	stages {
		stage ('JUnit Tests') {
			steps {
				sh 'mvn test'
			}
		}
	}
}