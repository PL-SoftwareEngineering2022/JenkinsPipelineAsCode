// pipeline > stage > steps > retry/timeouts
// retry: retries x number of times, the code in its code block
// timeout: waits for a specified  period of time before executing its code block
// the retries and timeouts are different from the ones in the option section as the apply only to a block of code
    // and can be nested within each other or used separately.
        // retries and timeouts are also available as steps provided by the workflow basic steps plugin, 
        // which is installed as part of the initial Jenkins setup.
        // sleep and error are also provided by the same plugin
//ref: https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/
// you can use retries and timeouts separately or nested.

// separately: Retries
pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                retry(3) {
                    echo "before throwing the error"
                    error "error in retry"
                }
                
                echo "after retry(3)"
            }
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-steps-retry-timeouts
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] retry
[Pipeline] {
[Pipeline] echo
before throwing the error
[Pipeline] error
[Pipeline] }
ERROR: error in retry
Retrying
[Pipeline] {
[Pipeline] echo
before throwing the error
[Pipeline] error
[Pipeline] }
ERROR: error in retry
Retrying
[Pipeline] {
[Pipeline] echo
before throwing the error
[Pipeline] error
[Pipeline] }
[Pipeline] // retry
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: error in retry
Finished: FAILURE

// separately: timeouts
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                timeout(time: 1, unit: 'SECONDS') //DAYS, HOURS, MICROSECONDS, MILLISECONDS, MINUTES, NANOSECONDS, SECONDS
                {
                    echo "sleeping in timeout"
                    sleep 2
                }
            }
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/timeouts
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] timeout
Timeout set to expire in 1 sec
[Pipeline] {
[Pipeline] echo
sleeping in timeout
[Pipeline] sleep
Sleeping for 2 sec
Cancelling nested steps due to timeout
[Pipeline] }
[Pipeline] // timeout
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Timeout has been exceeded
Finished: ABORTED


// nested: timeouts and retries
pipeline{
    agent any 
    stages {
        stage('Build') {
            steps{
                retry(3) {
                    timeout(time: 1, unit: 'SECONDS') {
                        sleep 2
                    }
                    echo "after timeout"
                }
            }
        }
    }
}
//console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/nested-retries-timeouts
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] retry
[Pipeline] {
[Pipeline] timeout
Timeout set to expire in 1 sec
[Pipeline] {
[Pipeline] sleep
Sleeping for 2 sec
Cancelling nested steps due to timeout
[Pipeline] }
[Pipeline] // timeout
[Pipeline] }
Timeout has been exceeded
Retrying
[Pipeline] {
[Pipeline] timeout
Timeout set to expire in 1 sec
[Pipeline] {
[Pipeline] sleep
Sleeping for 2 sec
Cancelling nested steps due to timeout
[Pipeline] }
[Pipeline] // timeout
[Pipeline] }
Timeout has been exceeded
Retrying
[Pipeline] {
[Pipeline] timeout
Timeout set to expire in 1 sec
[Pipeline] {
[Pipeline] sleep
Sleeping for 2 sec
Cancelling nested steps due to timeout
[Pipeline] }
[Pipeline] // timeout
[Pipeline] }
[Pipeline] // retry
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Timeout has been exceeded
Finished: ABORTED

