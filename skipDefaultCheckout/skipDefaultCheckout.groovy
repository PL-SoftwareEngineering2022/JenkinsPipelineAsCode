// pipeline > stage > options > skipDefaultCheckout
// skip checking out code from source control by default in the agent directive (can be both pipeline and stage level)
// it prevents the build from checking out the source code from the repository automatically

// this will be a multipipeline job to make use of the build configuration,
// which allows us to specify the location of the Jenkinsfile in the repo

