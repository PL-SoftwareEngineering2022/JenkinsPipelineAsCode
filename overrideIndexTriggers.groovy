// allows overridding default treatment of branch indexing triggers
// if branch indexing triggers are disabled at the multibranch or organization label, options{overrideIndexTriggers(true)} will enable them for this job only.
// otherwise. options{overrideIndexTriggers(false)} will disable branch indexxing triggers for this job only

// multi-branch pipeline
// when set to true, Jenkins will trigger builds on new commits.
pipeline {
    agent any
    options {
        timestamps()
        overrideIndexTriggers(true)
    }
    stages {
        stage('build'){
            steps {
                echo "hello world"
            }
        }
    }
}

pipeline {
    agent any
    options {
        timestamps()
        overrideIndexTriggers(false)
    }
    stages {
        stage('build'){
            steps {
                echo "hello world 2"
            }
        }
    }
}