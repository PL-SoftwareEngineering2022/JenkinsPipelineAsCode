// used with docker or dockerfile top-level agent.
// When specified, each stage will run in a new container instance on the same node, rather than all stages running in the same container instance.

pipeline{
    agent any {
        docker{
            image 'ubuntu:latest'
        }
    }
    options{
        newContainerPerStage()
    }
    stages{
        stage('Build'){
            steps {
                sh 'sudo cat /etc/apt/sources.list.d/docker.list'
            }
        }
        stage('Deploy'){
            steps{
                sh 'sudo cat /etc/apt/sources.list.d/docker.list'
            }
        }
    }
}