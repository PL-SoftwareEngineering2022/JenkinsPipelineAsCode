pipeline{
    agent any
    stages{
        stage("Build"){
            steps{
                echo "Hello world!"
            }
        }
    }
}
//declarative pipelines start with the "pipeline" while scripted syntax pipeline starts with "node"
// within each pipleline are stages, which are further broken down into stage and steps.

//agent any
//console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-helloworld
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
Hello world!
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


as compared to pipeline with agent none:
// agent none
pipeline{
    agent none
    stages{
        stage("Build"){
            steps{
                echo "Hello world!"
            }
        }
    }
}
//console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] stage
[Pipeline] { (Build)  
[Pipeline] echo
Hello world!
[Pipeline] }
[Pipeline] // stage
[Pipeline] End of Pipeline
Finished: SUCCESS