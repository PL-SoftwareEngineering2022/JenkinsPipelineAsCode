// branch condition specifies that the stage will execute only when the build is building a certain Branch
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