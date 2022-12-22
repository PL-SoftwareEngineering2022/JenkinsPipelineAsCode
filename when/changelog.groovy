// changelog condition only allows the stage to execute when the commit message fulfills a certain regular expression.
// mulitibranch pipeline

// git commit -m "prefix some_text suffix"
// git push -u origin main

pipeline {
    agent any
    stages{
        stage('Build'){
            when {
                changelog '.*some_text.*'
            }
            steps {
                echo "hello world changelog"
            }
        }
    }
}

// does not meet the regular expression:
// git commit -m "prefix some suffix"
// git push -u origin main
// when the job is now run, the stage will be skipped because the change log does not meet the regular expression that is expected

