pipeline {
    agent { docker 'maven:3.3.3' }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -V -U -e clean package'
            }
        }

        stage('Archive') {
            junit allowEmptyResults: true, testResults: '**/target/**/TEST*.xml'
        }
    }
}