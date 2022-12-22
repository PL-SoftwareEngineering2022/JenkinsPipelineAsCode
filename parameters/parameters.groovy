pipeline {
    agent any
    parameters {
        string(name: 'environment', defaultValue: 'Dev', description: 'Environment to build for (Valid values: Dev, Test, Prod)')
        string(name: 'version', defaultValue: '1.0', description: 'version number to build for')
        booleanParam(name: 'to_deploy_to_environment', defaultValue: true, description: '')
        choice(choices: 'US_CENTRAL1\nEUROPE-WEST1', description: 'what GCP region? ', name: 'region') 
        text(name: 'myText', defaultValue:'myTextValue', description:'myText')
        password(name: 'myPassword', defaultValue: 'myPasswordValue', description: 'myDescription')
        file(name: 'myFile', description: 'myFileDescription') // warning: 'file' parameter deprecated in favor of 'withFileParameter'. NB!: see reference #7-9 on file parameters
        credentials(name: 'myCredentials', description: 'myCredentialsDescription', required:true) // credentials dropdown list
    }
     stages{
         stage('Build'){
             steps{
                 echo " We are building for ${params.environment}, ${params.version}, and we are deploying to environment: ${params.deploy_to_environment}"
                 echo "region:${params.region}, myText: ${params.myText}, myPassword ${params.myPassword}, and myFile: ${params.myFile}"
                 echo "selected credentials is ${params.myCredentials}"
             }
         }
     }
}

Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/parameters
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] echo
 We are building for Dev, 1.0, and we are deploying to environment: null
[Pipeline] echo
region:US_CENTRAL1, myText: myTextValue, myPassword myPasswordValue, and myFile: null
[Pipeline] echo
selected credentials is null
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

// subsequent builds can be run under 'build with parameters'
// also, those parameters are now viewable under 'configure'