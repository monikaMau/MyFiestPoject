pipeline {
    agent any
    triggers {
        cron('30 0 * * *') // 12:25 AM daily
    }
    environment {
        MAVEN_HOME = "C:\\Program Files\\Apache\\maven"
        REPORT_DIR = "C:\\QAReports"
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
                bat "\"%MAVEN_HOME%\\bin\\mvn\" clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "\"%MAVEN_HOME%\\bin\\mvn\" clean test -DsuiteXmlFile=src/test/resources/testng.xml"
                }
            }
        }
        stage('Publish Reports') {
            steps {
                bat "if not exist \"%REPORT_DIR%\" mkdir \"%REPORT_DIR%\""
                bat "xcopy /s /y target\\surefire-reports\\* \"%REPORT_DIR%\""
                publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'Selenium TestNG Report'
                ])
            }
        }
    }
}
