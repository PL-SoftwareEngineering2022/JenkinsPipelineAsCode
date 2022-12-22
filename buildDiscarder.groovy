// persist artifacts and console output for the specific number of recent pipeline runs
// It instructs Jenkins to maintain a number of build runs for the job

pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '1'))
    }
    stages {
        stage('Build'){
            steps {
                echo "hello world"
            }
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/buildDiscarder
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
hello world
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

