// 'buildingTag' condition only allows the tage to execute when we are building from a tag
// 'tag' condition only allows the stage to execute when we are building from a specific tag ot tag name that fits a particular pattern


// multibranch pipeline. Also, you have to include new behavior in Jenkins: discover tags
// tag the master branch: git tag 1.0; git push origin --tags
// this tag is now viewable under Releases on Github
// after "scanning repository now" in Jenkins, it will now show the tag has been added and this is also viewable when you click on the pipeline
// click on the tag and do a build.
// 'buildingTag'
pipeline {
    agent any
    stages {
        stage('Build') {
            when {
                buildingTag()
            }
            steps {
                echo "hello world building tag"
            }
        }
    }
}

// modifying the build to use a tag condition
// git tag 2.0; git push origin --tags
// now delete the old tag: git push origin :refs/tags/1.0
// git tag -fa 1.0 (comment: moving tag 1.0)
// git push origin --tags
// this will point both tags to the same commit and so jenkins will build from the latest tag, not tag 1.0
// building tag 2.0 will execute stage but tag 1.0 will now skip stage execution due to when conditional
pipeline {
    agent any
    stages {
        stage('Build') {
            when {
                tag "2.0"
            }
            steps {
                echo "hello world building tag"
            }
        }
    }
}

// build from tag pattern
// git push -u origin main
// git tag release-to-prod
// git push origin --tags
// git push origin :refs/tags/2.0
// git tag -fa 2.0 (comment: moving tag 2.0)
// git push origin --tags
// Scan Repository now -- release-to-prod has been picked up
// build for tag 2.0 will now skip stage due to when conditional, but a build for release-to-prod will execute the stage

pipeline {
    agent any
    stages {
        stage('Build') {
            when {
                tag "release-*"
            }
            steps {
                echo "hello world building tag"
            }
        }
    }
}

// also see comparator:
// ref: https://jenkins.io/doc/book/pipeline/syntax/#when