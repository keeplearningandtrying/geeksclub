pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './mvnw -B -V -U -e clean package'
            }
        }

        stage('Archive') {
            steps {
                junit allowEmptyResults: true, testResults: '**/target/**/TEST*.xml'
            }
        }
    }
}