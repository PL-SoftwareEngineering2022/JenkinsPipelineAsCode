// changeRequest condition only allows stage to execute when there is a pull request in the Jenkinsfile.
// multibranch pipeline job
// When no parameters are passed the stage runs on every change request, for example: when { changeRequest() }.
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

// you can also use a few attributes when using 'changeRequest', like id, title, etc..
    // https://jenkins.io/doc/book/pipeline/syntax/#when
// By adding a filter attribute with parameter to the change request, the stage can be made to run only on matching change requests. 
// Possible attributes are id, target, branch, fork, url, title, author, authorDisplayName, and authorEmail. 
// Each of these corresponds to a CHANGE_* environment variable, for example: when { changeRequest target: 'master' }.

// this will only execute the stage when the title is "when-pr"
pipeline {
    agent any
    stages{
        stage('Build'){
            when {
                changeRequest title:"when-pr"
            }
            steps {
                echo "hello world changing request"
            }
        }
    }
}

