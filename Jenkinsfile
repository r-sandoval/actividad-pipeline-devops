pipeline {
    agent any

    stages {
        stage('Clonar Repositorio') {
            steps {
                checkout scm
            }
        }

        stage('Compilar y Empaquetar') {
            steps {
                // Compila usando un contenedor temporal de Maven con Java 17
                sh 'docker run --rm -v "${WORKSPACE}":/usr/src/mymaven -w /usr/src/mymaven maven:3.8-openjdk-17-slim mvn clean package -DskipTests'
            }
        }

        stage('Construir Imagen Docker') {
            steps {
                sh 'docker build -t sucursal-vehiculos:latest .'
            }
        }

        stage('Desplegar Contenedor') {
            steps {
                sh 'docker stop sucursal-app || true'
                sh 'docker rm sucursal-app || true'
                sh 'docker run -d --name sucursal-app -p 9090:8080 sucursal-vehiculos:latest'
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado con éxito.'
        }
        failure {
            echo 'Hubo un fallo en la ejecución del pipeline.'
        }
    }
}
