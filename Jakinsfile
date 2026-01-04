pipeline {

    agent any

    triggers {
        cron('H/5 * * * *')
    }

    stages {

        stage('Step 1 - Get Code') {
            steps {
                echo 'Pulling code from Git'
            }
        }

        stage('Step 2 - Build') {
            steps {
                echo 'Building the application'
            }
        }

        stage('Step 3 - Test') {
            steps {
                echo 'Running tests'
            }
        }

        stage('Step 4 - Deploy') {
            steps {
                echo 'Deploying application'
            }
        }
    }

    post {
        success {
            echo 'SUCCESS: Pipeline finished'
        }
        failure {
            echo 'FAILED: Pipeline error'
        }
    }
}
