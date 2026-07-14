pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    stages {
        stage('Descargar Codigo') {
            steps {
                git branch: 'main', url: 'https://github.com/r-sandoval/actividad-pipeline-devops.git'
            }
        }

        stage('Compilar Proyecto') {
            steps {
                sh 'mvn clean package -DskipTests'
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
                // Unimos la app a la red virtual y exponemos el puerto 9090
                sh 'docker run -d --name sucursal-app --network sucursal-net -p 9090:8080 sucursal-vehiculos:latest'
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado con éxito.'
        }
        failure {
            echo 'El pipeline ha fallado. Revisa los logs.'
        }
    }
}
