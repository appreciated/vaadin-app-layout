node {
    def app

    stage('Checkout source') {
        checkout scm
    }

    stage('Build project') {
        echo 'Packaging...'
        sh 'mvn clean deploy'
    }

    stage('Run sonar') {
        sh "mvn --settings settings.xml -Dsonar.branch=${BRANCH_NAME} sonar:sonar"
    }
}
