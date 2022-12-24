// pollSCM: Accepts a cron-style string to define a regular interval at which Jenkins should check for new source changes. 
// If new changes exist, the Pipeline will be re-triggered. For example: triggers { pollSCM('H */4 * * 1-5') }
pipeline{
    agent any
    triggers{
        pollSCM('* * * * *')
    }
    stages{
        stage('Build'){
            steps{
                checkout([$class: 'GitSCM',
                branches: [[name: "origin/main"]],
                userRemoteConfigs: [[
                url: 'https://github.com/PL-SoftwareEngineering2022/JenkinsPipelineAsCode.git']]])
            }
        }
    }
}