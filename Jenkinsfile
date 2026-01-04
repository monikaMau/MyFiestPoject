pipeline {
    agent any

    triggers {
        cron('H/5 * * * *')
    }

    tools {
        maven 'Maven'  // Make sure "Maven" is configured in Jenkins
    }

    stages {

        stage('Checkout Code') {
    steps {
        git branch: 'selenium_prpject', url: 'https://github.com/monikaMau/MyFiestPoject.git'
    }
}

        stage('Build Project') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Deploy Application (CD)') {
            steps {
                echo 'Deploying application to staging server'
                // Example: copy files to a folder
                bat 'xcopy /s /y app_folder C:\\deploy\\app'
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
