pipeline {
    agent any

    triggers {
        // Cron format: MIN HOUR DOM MON DOW
         // Runs every day at 12:25 AM
        cron('25 0 * * *')
    }

    stages {
        stage('Checkout Code') {
            steps {
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
                bat 'mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml'
            }
        }

        stage('Deploy Application (CD)') {
            steps {
                echo 'Deploying application to staging server'
                bat '''
                if exist target\\* (
                    xcopy /s /y target\\* C:\\deploy\\app
                ) else (
                    echo No build artifact found to deploy
                )
                '''
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing Selenium HTML TestNG reports'
                publishHTML([
                    reportName: 'Selenium TestNG Report',
                    reportDir: 'target\\surefire-reports',
                    reportFiles: 'index.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline finished with failures.'
        }
    }
}
