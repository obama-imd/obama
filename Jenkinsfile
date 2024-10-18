#!groovy

node {
    def gradle = "./gradlew"

    environment {
        DB_URL = credentials('DB_URL')
        DB_USERNAME = credentials('DB_USERNAME')
        DB_PASSWORD = credentials('DB_PASSWORD')
        SENHA_APP_EMAIL = credentials('SENHA_APP_EMAIL')
        JWT_SECRET = credentials('JWT_SECRET')
        DB_NAME = credentials('DB_NAME')
    }

    try {
        stage("Clone the project") {
            git branch: 'main', url: 'https://github.com/obama-imd/obama.git'
        }

        stage("Create .env file") {
            script {
                writeFile file: './.env', text: """
                DB_URL=${env.DB_URL}
                DB_USERNAME=${env.DB_USERNAME}
                DB_PASSWORD=${env.DB_PASSWORD}
                SENHA_APP_EMAIL=${env.SENHA_APP_EMAIL}
                JWT_SECRET=${env.JWT_SECRET}
                DB_NAME=${env.DB_NAME}
                """
            }
        }

        stage("Checkout") {
            checkout scm
        }

        stage("Build Application") {
            sh "chmod +x ./gradlew"
            sh "${gradle} clean build -x test"
        }

        stage("Test") {
            sh "${gradle} test"
        }

        stage("Jacoco") {
            sh "${gradle} jacocoTestReport"
        }

        stage("Docker build") {
            sh "docker build -t obamaapi ."
        }

        stage("Running the app") {
            sh "docker-compose stop obama-app"
            sh "docker-compose up -d"
        }
    } catch(Exception e) {
        echo "Deployment error. Cause: ${e}"
        throw e
    }
}
