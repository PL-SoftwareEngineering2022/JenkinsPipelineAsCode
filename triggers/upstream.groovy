// upstream: it triggers the current job when any of the upstream jobs that are specified have been completed.
// Accepts a comma-separated string of jobs and a threshold. When any job in the string finishes with the minimum threshold, the Pipeline will be re-triggered. 
// For example: triggers { upstream(upstreamProjects: 'job1,job2', threshold: hudson.model.Result.SUCCESS) }
pipeline{
    agent any
    triggers {
        upstream(upstreamProjects: 'pipeline-triggers-upstream-job1,pipeline-triggers-upstream-job2',
        threshold: hudson.model.Result.SUCCESS)//UNSTABLE, NOT_BUILT, ABORTED
    }
    stages{
        stage('Build'){
            steps{
                echo 'pipeline-triggers-upstream executed'
            }
        }
    }
}