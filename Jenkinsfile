node {

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