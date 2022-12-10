// pipeline > stage > options > timeout
// timeout: sets a timeout period for this stage after which Jenkins should abort the stage
// steps in the options directive are invoked before entering the agent or checking any when conditions
// with timeout, we can specify a waiting period before the steps section is executed
// 

pipeline {
    agent any
    
    stages{
        stage('Build') {
            options {
                timeout(time: 1, unit: 'SECONDS') //DAYS, HOURS, MICROSECONDS, MILLISECONDS, MINUTES, NANOSECONDS, SECONDS
            }
            steps {
                echo 'Hello World'
                sleep 2 // the sleep command of two seconds  in steps is longer than the timeout set which will cause the stage to timeout
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-options-timeout
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] timeout
Timeout set to expire in 1 sec
[Pipeline] {
[Pipeline] echo
Hello World
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
