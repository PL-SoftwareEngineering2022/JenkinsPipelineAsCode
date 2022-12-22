// Disallows concurrent executions of the pipeline. Can be useful for preventing simultaneous accesses to shared resources, etc.
// this keeps the next job as pending until the current build is completed
pipeline{
    agent any
    options {
        disableConcurrentBuilds()
    }
    stages{
        stage('build'){
            steps {
                sleep(time: 10, unit: 'SECONDS')
                echo "Hello world"
            }
        }
    }
}

