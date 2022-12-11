// 'equals' condition:
pipeline {
    agent any
    environment{
        some_name="Phyllis"
    }
    stages{
        stage('Build') {
            when {
                equals expected: "Phyllis", actual: some_name
            }
            steps {
                echo "${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-equals
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
Phyllis
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

// setting the value of 'some_name' to something else:
// this will evaluate the 'when' conditional as false. So the 'equals' condition is not fulfilled, thus the stage is not executed
pipeline {
    agent any
    environment{
        some_name="Likimani"
    }
    stages{
        stage('Build') {
            when {
                equals expected: "Phyllis", actual: some_name
            }
            steps {
                echo "${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-equals
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