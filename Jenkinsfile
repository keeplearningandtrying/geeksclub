pipeline {
    agent any
    tools {
        maven 'apache-maven-3.3.9'
    }
    stages {
        stage('Checkout') {
                git 'https://github.com/sivaprasadreddy/geeksclub.git'
        }

        stage('Build') {
            sh 'mvn -B -V -U -e clean package'
        }

        stage('Archive') {
            junit allowEmptyResults: true, testResults: '**/target/**/TEST*.xml'
        }
    }
}