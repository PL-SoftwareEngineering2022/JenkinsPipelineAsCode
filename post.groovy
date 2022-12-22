// reference: https://www.jenkins.io/doc/book/pipeline/syntax/#post-conditions 
// the post can appear both at the end of a stage or at the end of the pipeline.
// The post section defines one or more additional steps that are run upon the completion of a Pipeline’s or stage’s run (depending on the location of the post section within the Pipeline). 
// 'post' can support any of the following post-condition blocks: always, changed, fixed, regression, aborted, failure, success, unstable, unsuccessful, and cleanup. 
// These condition blocks allow the execution of steps inside each condition depending on the completion status of the Pipeline or stage. 
// The condition blocks are executed in the order shown below.

// Required - No
// Parameters- None
// Allowed- In the top-level pipeline block and each stage block.

// Conditions:
// always
// Run the steps in the post section regardless of the completion status of the Pipeline’s or stage’s run.

// changed
// Only run the steps in post if the current Pipeline’s run has a different completion status from its previous run.

// fixed
// Only run the steps in post if the current Pipeline’s run is successful and the previous run failed or was unstable.

// regression
// Only run the steps in post if the current Pipeline’s or status is failure, unstable, or aborted and the previous run was successful.

// aborted
// Only run the steps in post if the current Pipeline’s run has an "aborted" status, usually due to the Pipeline being manually aborted. 
// This is typically denoted by gray in the web UI.

// failure
// Only run the steps in post if the current Pipeline’s or stage’s run has a "failed" status, typically denoted by red in the web UI.

// success
// Only run the steps in post if the current Pipeline’s or stage’s run has a "success" status, typically denoted by blue or green in the web UI.

// unstable
// Only run the steps in post if the current Pipeline’s run has an "unstable" status, usually caused by test failures, code violations, etc. 
// This is typically denoted by yellow in the web UI.

// unsuccessful
// Only run the steps in post if the current Pipeline’s or stage’s run has not a "success" status. 
// This is typically denoted in the web UI depending on the status previously mentioned (for stages this may fire if the build itself is unstable).

// cleanup
// Run the steps in this post condition after every other post condition has been evaluated, regardless of the Pipeline or stage’s status.


// Conventionally, the post section should be placed at the end of the Pipeline.
// Post-condition blocks contain steps the same as the steps section.


// both the 'always' and 'success' post blocks are executed
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
                // error("Build failed")
            }
        }
    }
    post { 
        // only runs when the current pipeline or stage has a "success" status
        success { 
            echo 'I will always say Hello again after success!'
        }
        // only runs if the current pipline or stage has a "failed" status
       failure { 
            echo 'I will fail to say Hello after a failure!'
        }
        // runs regardless of the completion status of the pipeline of stage run
        always { 
            echo 'I will always say Hello regardless of fail or success!'
        }
    }
}
//console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Example)
[Pipeline] echo
Hello World
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
I will always say Hello regardless of fail or success!
[Pipeline] echo
I will always say Hello again after success!
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

// with error: the 'always' and 'failure' are executed
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
                error("Build failed")
            }
        }
    }
    post { 
        // only runs when the current pipeline or stage has a "success" status
        success { 
            echo 'I will always say Hello again after success!'
        }
        // only runs if the current pipline or stage has a "failed" status
       failure { 
            echo 'I will fail to say Hello after a failure!'
        }
        // runs regardless of the completion status of the pipeline of stage run
        always { 
            echo 'I will always say Hello regardless of fail or success!'
        }
    }
}
// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Example)
[Pipeline] echo
Hello World
[Pipeline] error
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
I will always say Hello regardless of fail or success!
[Pipeline] echo
I will fail to say Hello after a failure!
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: Build failed
Finished: FAILURE


// changed: this block will keep getting executed for as long as the last pipeline or stage build status is changed from the last build.
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Building'
                // error("Build failed")
                script {
                currentBuild.result = 'UNSTABLE'
                }
            }
        }
    }
    post { 
        // only if the current pipeline or stage's run has a different completion status from its previous run.
        changed { 
            echo 'I will always say Hello again after change!'
        }
    }
}
// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Example)
[Pipeline] echo
Building
[Pipeline] script
[Pipeline] {
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
I will always say Hello again after change!
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: UNSTABLE


// unstable: only runs when the current pipeline or stage has an unstable status
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Building'
                // error("Build failed")
                script {
                currentBuild.result = 'UNSTABLE'
                }
            }
        }
    }
    post { 
        // only if the current pipeline or stage's run has a different completion status from its previous run.
        changed { 
            echo 'I will always say Hello again after change!'
        }
        // only runs the steps if the cirrent pipeline/stage has an "unstable" status, usually caused by test failures, code violations, etc.
        unstable { 
            echo 'I will always say Hello again after an "unstable" build!'
        }
    }
}
// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Example)
[Pipeline] echo
Building
[Pipeline] script
[Pipeline] {
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
I will always say Hello again after an unstable build!
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: UNSTABLE


// fixed:
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Building'
                // error("Build failed")
                // script {
                // currentBuild.result = 'UNSTABLE'
                // }
            }
        }
    }
    post { 
        // only runs if the current pipeline/stage's run is successful and the previous run failed or was unstable
        fixed { 
            echo 'post: fixed is called'
        }
    }
}
//console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Example)
[Pipeline] echo
Building
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
post: fixed is called
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


//regression: only runs if the current pipleline/stage's status is failure, unstable or aborted and the previous run was successful.
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building'
                error("Build failed")
            }
        }
    }
    post { 
        regression { 
            echo 'post: regression is called'
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
Building
[Pipeline] error
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
post: regression is called
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: Build failed
Finished: FAILURE


// aborted: // only runs if teh current pipleine/stage run has an "aborted" status; usually due to the pipeline being manually aborted
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building'
                // error("Build failed")
                script {
                currentBuild.result = 'ABORTED'
                }
            }
        }
    }
    post { 
        aborted { 
            echo 'post: aborted called'
        }
    }
}
// console output:
 Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
Building
[Pipeline] script
[Pipeline] {
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
post: aborted called
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: ABORTED

// cleanup: runs after every other post condition has been evaluated regardless of the pipeline/stage status
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
                // error("Build failed")
            }
        }
    }
    post { 
        success { 
            echo 'I will always say Hello again after success!'
        }
       failure { 
            echo 'I will fail to say Hello after a failure!'
        }
        always { 
            echo 'I will always say Hello regardless of fail or success!'
        }
        cleanup{
            echo "post: cleanup is called"
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Example)
[Pipeline] echo
Hello World
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
I will always say Hello regardless of fail or success! // please note the order in which the post conditions are called. "always" is always first and "cleanup" is always last
[Pipeline] echo
I will always say Hello again after success!
[Pipeline] echo
post: cleanup is called
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


// cleanup with failure:
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
                error("Build failed")
            }
        }
    }
    post { 
        success { 
            echo 'I will always say Hello again after success!'
        }
       failure { 
            echo 'I will fail to say Hello after a failure!'
        }
        always { 
            echo 'I will always say Hello regardless of fail or success!'
        }
        cleanup{
            echo "post: cleanup is called"
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-post
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Example)
[Pipeline] echo
Hello World
[Pipeline] error
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
I will always say Hello regardless of fail or success!
[Pipeline] echo
I will fail to say Hello after a failure!
[Pipeline] echo
post: cleanup is called
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: Build failed
Finished: FAILURE