// timestamp: prepends all console output generated during this stage with the time at which the line was emitted
        // for example: options{timestamp()}
// requires timestamper plugin. 
        //ref: https://plugins.jenkins.io/timestamper

pipeline {
    agent any
    stages {
        stage('Build'){
            options{
                timestamps() // note: timestamps is only in the Build stage and not in the Test stage
            }
            steps{
                echo 'Hello world in Build'
                echo 'Hello world in Build again'
            }
        }
        stage('Test') {
            
            steps{
                echo 'Hello world in Test'
                echo 'Hello world in Test again'
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stages-options-timestamps
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] timestamps
[Pipeline] {
[Pipeline] echo
17:04:18  Hello world in Build  // you can see there are now timestamps in the Build stage but none in the Test stage
[Pipeline] echo
17:04:18  Hello world in Build again
[Pipeline] }
[Pipeline] // timestamps
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Test)
[Pipeline] echo
Hello world in Test
[Pipeline] echo
Hello world in Test again
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

// you can optionally do this at the global level, under pipeline instead of any one stage:
pipeline {
    agent any
    options{
                timestamps() // timestamps is now going to be available for both Build stage and the Test stage
            }
    stages {
        stage('Build'){
            
            steps{
                echo 'Hello world in Build'
                echo 'Hello world in Build again'
            }
        }
        stage('Test') {
            
            steps{
                echo 'Hello world in Test'
                echo 'Hello world in Test again'
            }
        }
    }
}
//console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stages-options-timestamps
[Pipeline] {
[Pipeline] timestamps
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
11:23:04  Hello world in Build
[Pipeline] echo
11:23:04  Hello world in Build again
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Test)
[Pipeline] echo
11:23:04  Hello world in Test
[Pipeline] echo
11:23:04  Hello world in Test again
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // timestamps
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

// ref: https://issues.jenkins.io/browse/JENKINS-48556?focusedCommentId=324614&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel
// issue: timestamps() randomly not recognized as a valid option

// we can also use timestamps as a block residing in the steps block, instead of using it as a function. note also, options is not used at all
// You cannot use the timestamps block at the pipeline or global level as this will cause a syntax error.
pipeline {
    agent any       
    stages {
        stage('Build'){
            steps{
                timestamps {
                    echo 'Hello world in Build'
                    echo 'Hello world in Build again'
                }
            }
        }
        stage('Test') {
            steps{
                echo 'Hello world in Test'
                echo 'Hello world in Test again'
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stages-options-timestamps
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] timestamps
[Pipeline] {
[Pipeline] echo
11:42:48  Hello world in Build
[Pipeline] echo
11:42:48  Hello world in Build again
[Pipeline] }
[Pipeline] // timestamps
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Test)
[Pipeline] echo
Hello world in Test
[Pipeline] echo
Hello world in Test again
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

