// skipStagesAfterUnstable: will skip stages once the build status has gone to UNSTABLE. For example: options { skipStagesAfterUnstable() }
// It helps us to skip downstream stages unce the build has gone to unstable. 
// * do this when the subsequent stages are dependent on the upstram stages to be successful.


pipeline {
    agent any
    // options {
    //     skipStagesAfterUnstable()
    // }
    stages {
        stage('Build'){
            steps{
                echo "setting build to unstable"
                script {
                    currentBuild.result = 'UNSTABLE'
                }
            }
        }
        stage('Deploy'){
            steps {
                echo "deploy is running"
            }
        }
    }
}
//console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/skipStagesAfterUnstable
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
setting build to unstable
[Pipeline] script
[Pipeline] {
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Deploy)
[Pipeline] echo
deploy is running
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: UNSTABLE


// the deployment stage will not be executed because the build stage is set to unstable:
pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build'){
            steps{
                echo "setting build to unstable"
                script {
                    currentBuild.result = 'UNSTABLE'
                }
            }
        }
        stage('Deploy'){
            steps {
                echo "deploy is running"
            }
        }
    }
}
// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/skipStagesAfterUnstable
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
setting build to unstable
[Pipeline] script
[Pipeline] {
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Deploy)
Stage "Deploy" skipped due to earlier stage(s) marking the build as unstable
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: UNSTABLE