pipeline > stage > when
// when: at least one of the following conditions must be specified. 
    // If there is more than one condition, all conditions must return true for the stage to execute.
// environment, equals, not, expression, allOf, anyOf, branch, changelog, changeset, changeRequest, buildingTag. tag, beforeAgent

// in this case we have a DEPLOY_TO environment variable. we have set the 'when' condition to specify that 
// the environment variable 'DEPLOY_TO' has to be of value 'production' before the stage can be executed.
pipeline {
    agent any
    environment {
        DEPLOY_TO = 'production'
    }
    stages{
        stage('Build'){
            when {
                environment name: 'DEPLOY_TO', value: 'production'
            }
            
            steps {
                echo 'deploying'
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-environment
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
deploying
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

// when the enviroment variable value is changed to different from the one set under pipeline than it is from the one under stage:
pipeline {
    agent any
    environment {
        DEPLOY_TO = 'test'
    }
    stages{
        stage('Build'){
            when {
                environment name: 'DEPLOY_TO', value: 'production'
            }
            
            steps {
                echo 'deploying'
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-environment
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
Stage "Build" skipped due to when conditional // note this change due to the DEPLOY_TO value not being the same as the one under pipeline
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS