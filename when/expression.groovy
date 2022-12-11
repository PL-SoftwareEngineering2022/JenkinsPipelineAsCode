// 'expression' condition:
// allows us to specify groovy expressions and evaluate them. for example:
pipeline{
    agent any
    environment{
        version ="1.0"
    }
    stages{
        stage('Build') {
            when {
                expression {
                    version == "1.0" //equals condition expecting to be evaluated as True
                }
            }
            steps {
                echo "building ${version}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-expression
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
building 1.0
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


// changing the version of the build to be 2.0:
// expected: the stage will not execute
pipeline{
    agent any
    environment{
        version ="2.0"
    }
    stages{
        stage('Build') {
            when {
                expression {
                    version == "1.0" // equals condition expecting to be evaluated as false
                }
            }
            steps {
                echo "building ${version}"
            }
        }
    }
}
// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-expression
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
Stage "Build" skipped due to when conditional
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS