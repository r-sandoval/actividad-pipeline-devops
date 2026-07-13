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
                // Se ejecuta la compilación empaquetando el archivo .war
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Construir Imagen Docker') {
            steps {
                // Construye la imagen de la sucursal usando el Dockerfile del repositorio
                sh 'docker build -t sucursal-vehiculos:latest .'
            }
        }

        stage('Desplegar Contenedor') {
            steps {
                // Detiene y elimina el contenedor si ya existe
                sh 'docker stop sucursal-app || true'
                sh 'docker rm sucursal-app || true'
                
                // Despliega usando la red de la máquina para conectar con MySQL local en el puerto 8080 nativo
                sh 'docker run -d --name sucursal-app --network host sucursal-vehiculos:latest'
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
