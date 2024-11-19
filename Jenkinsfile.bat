pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/vijaykumart2523/Caw_Studio_assginments.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                bat "mvn clean test"
            }
        }
    }

    post {
        success {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
        failure {
            echo "Build failed"
        }
    }
}
