pipeline{
    agent any
    stages{
        stage('Build Master') {
            when {
                branch 'master'
            }
            steps{
                echo "Buiding master"
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