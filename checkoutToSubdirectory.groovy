// perform the automatic source control checkout in a subdirectory of the workspace
// allows us to specify where to checkout the code to in Jenkins
// multibranch pipeline


pipeline{
    agent any
    options{
        checkoutToSubdirectory('someSubDir')
    }
    stages{
        stage('Build'){
            steps{
                echo 'hello world'
            }
        }
    }
}
