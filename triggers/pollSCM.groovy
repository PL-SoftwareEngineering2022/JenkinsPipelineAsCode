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

Started by an SCM change
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/triggers-pollSCM
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] checkout
The recommended git tool is: NONE
No credentials specified
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/triggers-pollSCM/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/PL-SoftwareEngineering2022/JenkinsPipelineAsCode.git # timeout=10
Fetching upstream changes from https://github.com/PL-SoftwareEngineering2022/JenkinsPipelineAsCode.git
 > git --version # timeout=10
 > git --version # 'git version 2.30.2'
 > git fetch --tags --force --progress -- https://github.com/PL-SoftwareEngineering2022/JenkinsPipelineAsCode.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision a53a5e2f6aa0fc3c8e7c6987f93b348117b76d6f (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f a53a5e2f6aa0fc3c8e7c6987f93b348117b76d6f # timeout=10
Commit message: "groovy-scripts"
 > git rev-list --no-walk 7f21052ee920be171abc747a72e0adf6c7fe0e60 # timeout=10
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS