// 'anyOf' requires that any of the conditions in it return true for the stage to execute
// the 'version' is set to 2.0 but we still expect the stage to execute, although it evaluates as false, because 'some_name' evaluates as true
pipeline {
    agent any
    environment {
        version = "2.0" 
        some_name = "Phyllis"
    }
    stages {
        stage('Build') {
            when {
                anyOf {
                    environment name: "version", value: "1.0"
                    environment name: "some_name", value: "Phyllis"
                }
            }
            steps {
                echo "Building anyOf ${version} ${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-anyOf
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
Building anyOf 2.0 Phyllis
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


// when both environmental variables do not match:
// because none of the environmental variables evaluate as true, then the stage will not execute.
pipeline {
    agent any
    environment {
        version = "2.0" 
        some_name = "Likimani"
    }
    stages {
        stage('Build') {
            when {
                anyOf {
                    environment name: "version", value: "1.0"
                    environment name: "some_name", value: "Phyllis"
                }
            }
            steps {
                echo "Building anyOf ${version} ${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-anyOf
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