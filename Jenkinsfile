pipeline {
    agent any

    tools {
        maven 'Maven'   // Name of Maven installation configured in Jenkins
        jdk 'JDK 1.8'   // Name of JDK installation configured in Jenkins
    }

    environment {
        // Add environment variables if needed
        DEPLOY_PATH = 'C:\\deploy\\app'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout from GitHub branch
                git branch: 'selenium_prpject',
                    url: 'https://github.com/monikaMau/MyFiestPoject.git'
            }
        }

        stage('Build Project') {
            steps {
                echo 'Building project with Maven'
                bat 'mvn clean compile'
            }
        }

        stage('Run Selenium Tests (Headless)') {
            steps {
                echo 'Running Selenium tests with TestNG suite'
                // Continue pipeline even if tests fail
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml'
                }
            }
        }

        stage('Deploy Application (CD)') {
            steps {
                echo 'Deploying application to staging server'
                // Example: copy artifact to deploy folder
                bat """
                if exist target\\* (
                    xcopy /s /y target\\* %DEPLOY_PATH%
                ) else (
                    echo No build artifact found to deploy
                )
                """
            }
        }
    }

    post {
        always {
            echo 'Publishing Selenium HTML TestNG reports'
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports',
                reportFiles: 'emailable-report.html', // Change to index.html if you have it
                reportName: 'Selenium TestNG Report'
            ])
        }

        success {
            echo 'Pipeline finished successfully!'
        }

        failure {
            echo 'Pipeline finished with failures.'
        }
    }
}
