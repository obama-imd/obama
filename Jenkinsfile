#!groovy

node {
    env.JAVA_HOME = tool(name: 'JDK 17', type: 'jdk')
    env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    def gradle = "./gradlew"

    try {
        stage("Clone the project") {
            git branch: 'main', url: 'https://github.com/obama-imd/obama.git'
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
            sh "sudo docker build -t obamaapi ."
        }

        stage("Running the app") {
            withCredentials([
                string(credentialsId: 'DB_URL', variable: 'DB_URL'),
                string(credentialsId: 'DB_USERNAME', variable: 'DB_USERNAME'),
                string(credentialsId: 'DB_PASSWORD', variable: 'DB_PASSWORD'),
                string(credentialsId: 'SENHA_APP_EMAIL', variable: 'SENHA_APP_EMAIL'),
                string(credentialsId: 'JWT_SECRET', variable: 'JWT_SECRET'),
                string(credentialsId: 'DB_NAME', variable: 'DB_NAME')
            ]) {
                sh """
                docker run -d \
                  --name obama-app \
                  -e DB_URL=${DB_URL} \
                  -e DB_USERNAME=${DB_USERNAME} \
                  -e DB_PASSWORD=${DB_PASSWORD} \
                  -e JWT_SECRET=${JWT_SECRET} \
                  -e SENHA_APP_EMAIL=${SENHA_APP_EMAIL} \
                  -p 8081:8081 \
                  obamaapi:latest
                """
            }
        }
    } catch(Exception e) {
        echo "Deployment error. Cause: ${e}"
        throw e
    } finally {
        deleteDir()
     }
}
