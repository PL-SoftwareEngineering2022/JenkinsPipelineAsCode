// pipeline > stage > input
// the stage will pause after any options have been applied, and before entering the stage's agent or evaluating its when condition.
// if the input is approved, the stage will then continue.
// Any parameters provided as part of the input submission will be available in the environment for the rest of the stage.
// It is useful of you need to allow user input which will affect the build flow.

pipeline {
    agent any
    
     stages{
         stage('Build'){
             input{
                 message "please specify the environment: " // allows us to specify a high level message for the input
                 ok "OK" // specify the text of the OK button
                 submitter "dummyuser,phyll-mamz@gmail.com,admin@localhost.com,phyll-mamz-jenkins-gcp" //(optional field) if ommitted, any user can submit the user input. 
                                                                // Submitter is where we can specify users or groups allowed to submit the user input, the user is the userid of the user on Jenkins. 
                                                                // You can specify multiple users by usign the delimitter comma
                                                                // beware to remove all white spaces between the ids other than the comma or it will throw an error
                 submitterParameter "whoIsSubmitter" // we can specify an environment variable that contains the submitter's ID. 
                                                     // If specified this is the name of the return value that will contain the id of the user that approves this input.
                                                    // as an example use case, the value can then be used to print out on the console, from where we can tell who approved the build
                 parameters{ // where we can specify the input controls that a user can interact with.
                     string(name: 'environment', defaultValue: 'Dev', description: 'Environment to build for (Valid values: Dev, Test, Prod)') // single text field using string
                     string(name: 'version', defaultValue: '1.0', description: 'version number to build for')
                     booleanParam(name: 'to_deploy_to_environment', defaultValue: true, description: '') // provides a tick box button
                     choice(choices: 'US_CENTRAL1\nEUROPE-WEST1', description: 'what GCP region? ', name: 'region') // provides a dropdown list. note: you have to use new line characters to separate the different options in the list
                     text(name: 'myText', defaultValue:'myTextValue', description:'myText')
                     password(name: 'myPassword', defaultValue: 'myPasswordValue', description: 'myDescription')
                     file(name: 'myFile', description: 'myFileDescription') // warning: 'file' parameter deprecated in favor of 'withFileParameter'. 
                                                                            // note: see reference #7-9 on file parameters
                     credentials(name: 'myCredentials', description: 'myCredentialsDescription', required:true) // credentials dropdown list
                 }
             }
             
             steps{
                 echo " We are building for ${environment}, ${version}, and we are deploying to environment: ${deploy_to_environment}"
                 echo "region:${region}, myText: ${myText}, myPassword ${myPassword}, and myFile: ${myFile}"
                 echo "submitter is ${whoIsSubmitter}"
                 echo "selected credentials is ${myCredentials}"
             }
         }
     }
}