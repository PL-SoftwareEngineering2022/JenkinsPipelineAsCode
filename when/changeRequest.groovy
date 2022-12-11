// changeRequest condition only allows stage to execute when there is a pull request in the Jenkinsfile.
// multibranch pipeline job
pipeline {
    agent any
    stages{
        stage('Build'){
            when {
                changeRequest()
            }
            steps {
                echo "hello world changing request"
            }
        }
    }
}
