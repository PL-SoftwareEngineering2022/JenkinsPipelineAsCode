// By default, the beforeAgent is set to false, which means that the script will execute the when directive on the remote agent, 
// assuming there is one, regardless of whether the when directive is going to return true or not.
// however, when the beforeAgent is set to true, the when directive will be evaluated first, and if the when directive returns true, 
// only then will the agent be entered to execute the stage

// By default, the when condition for a stage will be evaluated after entering the agent for that stage, if one is defined. 
// However, this can be changed by specifying the beforeAgent option within the when block. 
// If beforeAgent is set to true, the when condition will be evaluated first, and the agent will only be entered if the when condition evaluates to true.
pipeline {
    agent none
    stages {
        stage('Example Build') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Example Deploy') {
            agent {
                label "some-label"
            }
            when {
                beforeAgent true
                branch 'production'
            }
            steps {
                echo 'Deploying'
            }
        }
    }
}

pipeline {
    agent none
    environment {
        DEPLOY_TO ='dev'
    }
    stages {
        stage('build') {
            agent {
                label "worker 1"
            }
            when {
                beforeAgent false
                environment name: 'DEPLOY_TO', value: 'production'  // evaluates as false and skips due to when condition because env does not match
            }
            steps{
                echo 'Building'
            }
        }
    }
}


pipeline {
    agent none
    environment {
        DEPLOY_TO ='dev'
    }
    stages {
        stage('build') {
            agent any
            when {
                beforeAgent false
                environment name: 'DEPLOY_TO', value: 'dev'
            }
            steps{
                echo 'Building'
            }
        }
    }
}
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (build)
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/beforeAgent
[Pipeline] {
[Pipeline] echo
Building
[Pipeline] }
[Pipeline] // node
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] End of Pipeline
Finished: SUCCESS

pipeline {
    agent none
    environment {
        DEPLOY_TO ='dev'
    }
    stages {
        stage('build') {
            agent any
            when {
                beforeAgent true
                environment name: 'DEPLOY_TO', value: 'dev'
            }
            steps{
                echo 'Building'
            }
        }
    }
}
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (build)
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/beforeAgent
[Pipeline] {
[Pipeline] echo
Building
[Pipeline] }
[Pipeline] // node
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] End of Pipeline
Finished: SUCCESS