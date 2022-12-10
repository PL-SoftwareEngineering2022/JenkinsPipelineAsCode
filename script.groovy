// pipeline > stage > steps > script
// the script step allows us to write custom code in groovy.
// It allows us to program more complex flows beyond what the declative syntax allows. It uses groovy as the programming language.

// error: indicates an error in the current stage and prints out error message
// sleep: pauses teh pipeline build until the given amount of time has expired

pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                
                script {
                  
                  def name = "phyllis"
                  
                  if(name == "phyllis")
                    println("hi ${name}")
                  else
                    println("hi human")
                    
                  sleep 2
                  echo "end of script"
                    
                }
            }
        }
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-steps-script
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] script
[Pipeline] {
[Pipeline] echo
hi phyllis
[Pipeline] sleep
Sleeping for 2 sec
[Pipeline] echo
end of script
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS