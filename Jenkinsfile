pipeline {
    agent any

    triggers {
        cron('H/5 * * * *')
    }

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/monikaMau/MyFiestPoject.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Selenium Tests (Headless)') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            publishHTML([
                reportDir: 'target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'Selenium TestNG Report',
                keepAll: true,
                alwaysLinkToLastBuild: true
            ])
        }

        success {
            echo 'All Selenium tests passed'
        }

        failure {
            echo 'Some Selenium tests failed'
        }
    }
}
