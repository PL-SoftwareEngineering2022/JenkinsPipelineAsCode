// error: indicates that there is an error in the current stage and prints out the error message

// in this example we are setting options under stage to retry three times if the stage fails. Under step we have a few echo commands
// and used the error step to indicate an error which will cause the stage to fail and thus the script will reach the stage again.
// note: the "after error statement" will not be printed out as the script will bail out at the error step and retry the stage again

pipeline{
    agent any
    stages{
        stage('Build'){
            options{
                retry(3)
            }
            steps{
                echo "before error statement"
                error "error statements just got executed"
                echo "after error statement"
            }
        }
    }
}

//console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/errors
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] retry
[Pipeline] {
[Pipeline] echo
before error statement
[Pipeline] error
[Pipeline] }
ERROR: error statements just got executed
Retrying
[Pipeline] {
[Pipeline] echo
before error statement
[Pipeline] error
[Pipeline] }
ERROR: error statements just got executed
Retrying
[Pipeline] {
[Pipeline] echo
before error statement
[Pipeline] error
[Pipeline] }
[Pipeline] // retry
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: error statements just got executed
Finished: FAILURE

// please note that by setting the results of failure within the stage, it does not make the script retry the stage again... 
        //so the build will fail but therre will be no retry.
// what the script does is to use the currentBuild variable which represents the current build and sets the result to failure.
// the currentBuild variable is one among many Jenkins provides to our pipeline script.
pipeline{
    agent any
    stages{
        stage('Build'){
            options{
                retry(3)
            }
            steps{
                echo "before setting current build to FAILURE"
                script{
                    currentBuild.result = 'FAILURE'
                }
                echo "After setting current build to FAILURE"
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/errors
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] retry
[Pipeline] {
[Pipeline] echo
before setting current build to FAILURE
[Pipeline] script
[Pipeline] {
[Pipeline] }
[Pipeline] // script
[Pipeline] echo
After setting current build to FAILURE
[Pipeline] }
[Pipeline] // retry
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: FAILURE
