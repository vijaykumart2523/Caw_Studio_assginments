pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Use the credentials ID to access the repository
                git credentialsId: '90fe5ba5-a758-4bfb-b85b-7f101a13e0b9', url: 'https://github.com/vijaykumart2523/Caw_Studio_assginments.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                // Run Selenium tests
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy the application (if applicable)
                sh 'mvn deploy'
            }
        }
    }

    post {
        always {
            // Actions that should be executed at the end of the pipeline
            echo 'Pipeline finished.'
        }
    }
}
