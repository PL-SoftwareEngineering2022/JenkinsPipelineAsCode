// Docker: executes the pipeline with the given container which will dynamically be provisioned on a node preconfigured to accept docker based pipelined, 
// or on a node matching the optionally defined label parameter.

pipeline{
    agent{
        docker {
            image 'maven:3.5.3-jdk-10-slim'
        }
    }
    stages{
        stage('Build') {
            steps{
                sh 'mvn -v'
            }
        }
    }
}