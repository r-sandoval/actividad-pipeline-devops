pipeline {
    agent any
    
    tools {
        maven 'Maven3'
    }
    
    stages {
        stage('Clonar Repositorio') {
            steps {
                checkout scm
            }
        }
        
        stage('Compilar y Empaquetar (Maven)') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('Construir Imagen Docker') {
            steps {
                sh 'docker build -t app-sucursal:latest .'
            }
        }
        
        stage('Desplegar Contenedor') {
            steps {
                sh 'docker stop app-sucursal-container || true'
                sh 'docker rm app-sucursal-container || true'
                sh 'docker run -d --name app-sucursal-container -p 9090:9090 app-sucursal:latest'
            }
        }
    }
    
    post {
        success {
            echo '¡Pipeline ejecutado con éxito y contenedor desplegado!'
        }
        failure {
            echo 'Hubo un fallo en la ejecución del pipeline. Revisa los logs.'
        }
    }
}