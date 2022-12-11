//'not' condition:
// nesting the 'equals' condition inside 'not' condition
// the 'equals' evaluation returns true because we have "Phyllis" as the environmental variable while the 'equals' condition
// is expecting "Phyllis". However, the 'not' conditions negates and inverts that true value, therefore the stage is not executed.
pipeline {
    agent any
    environment{
        some_name="Phyllis"
    }
    stages{
        stage('Build') {
            when {
               not{
                    equals expected: "Phyllis", actual: some_name
               }
            }
            steps {
                echo "${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-not
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



// in this case the 'equals' evaluation returns false because we have "Likimani" as the environmental variable while the 'equals' condition
// is expecting Jeff. However, the 'not' condition inverts the false value into true, therefore the stage is executed.
pipeline {
    agent any
    environment{
        some_name="Likimani"
    }
    stages{
        stage('Build') {
            when {
               not{
                    equals expected: "Phyllis", actual: some_name
               }
            }
            steps {
                echo "${some_name}"
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-when-not
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
Likimani
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS