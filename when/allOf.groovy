// 'allOf' condition: requires all conditons in it to evaluate to true for the stage to execute

pipeline{
    agent any
    environment{
        version = "1.0"
        some_name = "Phyllis"
    }
    stages{
        stage('Build'){
            when{
                allOf {
                    environment name: "version", value: "1.0"
                    environment name: "some_name", value: "Phyllis"
                }
            }
            steps  {
                echo "Building allOf ${version} ${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-allOf
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
Building allOf 1.0 Phyllis
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

// when the version is changed or when one of the variables changes so the 
//'when' condition does not evaluate as true, so the stage will not be executed:
pipeline{
    agent any
    environment{
        version = "2.0" //this will cause the 'when' condition to evaluate as false
        some_name = "Phyllis"
    }
    stages{
        stage('Build'){
            when{
                allOf {
                    environment name: "version", value: "1.0"
                    environment name: "some_name", value: "Phyllis"
                }
            }
            steps  {
                echo "Building allOf ${version} ${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-allOf
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

