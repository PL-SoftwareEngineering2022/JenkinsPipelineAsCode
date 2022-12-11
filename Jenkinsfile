pipeline{
    agent any
    stages{
        stage('Build Main') {
            when {
                branch 'main'
            }
            steps{
                echo "Buiding main"
            }
        }
        stage('Build Dev') {
            when {
                branch 'dev'
            }
            steps{
                echo "Building dev"
            }
        }
    }
}