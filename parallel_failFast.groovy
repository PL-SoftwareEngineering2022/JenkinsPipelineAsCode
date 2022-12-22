// pipeline > stage > parallel
// This is useful if you want t run stages in parallel if there are no dependencies between them.

// this pipeline has a parallel block that contains other stages which means those stages will be run in parallel.
// the failFast keyword being set to true indicates that should one of the stages fail, the rest of the stages should also fail.
pipeline {
    agent any
    stages {
        stage('Stages running in parallel') {
            failFast true
            parallel {
                stage('Stage1'){
                    steps {
                        echo "Stage1 executing"
                        sleep 10
                    }
                }
                stage('Stage2'){
                    steps {
                        echo "Stage2 executing"
                        sleep 10
                    }
                }
                stage('stage3') {
                    steps {
                        echo "Stage3 executing"
                        sleep 10
                    }
                }
            }
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/parallel
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Stages running in parallel)
[Pipeline] parallel
[Pipeline] { (Branch: Stage1)
[Pipeline] { (Branch: Stage2)
[Pipeline] { (Branch: stage3)
[Pipeline] stage
[Pipeline] { (Stage1)
[Pipeline] stage
[Pipeline] { (Stage2)
[Pipeline] stage
[Pipeline] { (stage3)
[Pipeline] echo
Stage1 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] echo
Stage2 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] echo
Stage3 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // parallel
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


// running the same job in sequence:
// this pipeline runs slower as each of the sleep commands are run sequentially.
pipeline {
    agent any
    stages {
        stage('Stage1'){
            steps {
                echo "Stage1 executing"
                sleep 10
            }
        }
        stage('Stage2'){
            steps {
                echo "Stage2 executing"
                sleep 10
            }
        }
        stage('stage3') {
            steps {
                echo "Stage3 executing"
                sleep 10
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/parallel
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Stage1)
[Pipeline] echo
Stage1 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Stage2)
[Pipeline] echo
Stage2 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (stage3)
[Pipeline] echo
Stage3 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


// failFast:
// due to failure in stage3, it causes both stage1 and stage3 to fail
pipeline {
    agent any
    stages {
        stage('Stages running in parallel') {
            failFast true
            parallel {
                stage('Stage1'){
                    steps {
                        echo "Stage1 executing"
                        sleep 10
                    }
                }
                stage('Stage2'){
                    steps {
                        echo "Stage2 executing"
                        sleep 2 //set a sleep timer shorter than the other stages
                        error 'simulating error happened on Stage2'
                    }
                }
                stage('stage3') {
                    steps {
                        echo "Stage3 executing"
                        sleep 10
                    }
                }
            }
        }
    }
}

//console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/failFast
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Stages running in parallel)
[Pipeline] parallel
[Pipeline] { (Branch: Stage1)
[Pipeline] { (Branch: Stage2)
[Pipeline] { (Branch: stage3)
[Pipeline] stage
[Pipeline] { (Stage1)
[Pipeline] stage
[Pipeline] { (Stage2)
[Pipeline] stage
[Pipeline] { (stage3)
[Pipeline] echo
Stage1 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] echo
Stage2 executing
[Pipeline] sleep
Sleeping for 2 sec
[Pipeline] echo
Stage3 executing
[Pipeline] sleep
Sleeping for 10 sec
[Pipeline] error
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
Failed in branch Stage2
[Pipeline] }
[Pipeline] }
[Pipeline] // stage
[Pipeline] // stage
[Pipeline] }
Failed in branch Stage1
[Pipeline] }
Failed in branch stage3
[Pipeline] // parallel
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: simulating error happened on Stage2
Finished: FAILURE
