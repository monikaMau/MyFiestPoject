pipeline {
    agent any

    triggers {
        cron('H/5 * * * *') // every 5 minutes
    }

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'selenium_prpject', url: 'https://github.com/monikaMau/MyFiestPoject.git'
            }
        }

        stage('Build & Test Project') {
            steps {
                // compile + run tests to generate reports
                bat 'mvn clean test'
            }
        }

        stage('Deploy Application (CD)') {
            steps {
                echo 'Deploying application to staging server'
                // Adjust this path to your actual artifact
                // bat 'xcopy /s /y target\\seleniumframeworkproject-0.0.1-SNAPSHOT.jar C:\\deploy\\app'
            }
        }

        stage('Run Selenium Tests (Headless)') {
            steps {
                echo 'Selenium tests already ran in mvn test'
            }
        }
    }

    post {
        always {
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'Selenium TestNG Report'
            ])
        }

        success {
            echo 'Pipeline finished successfully'
        }

        failure {
            echo 'Pipeline failed'
        }
    }
}
