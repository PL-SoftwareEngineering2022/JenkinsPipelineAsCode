// pipeline > stage > environment Credentials
// these are not global variables provided by Jenkins but our own environmental variables specified in the script
pipeline {
    agent any
    environment{
        name1 = "Phyllis"
        name2 = "Likimani"
    }
    stages {
        stage('Build'){
            environment {
                name3 = "PhyllisL"
            }
            steps{
                echo "name1 ${name1}"
                echo "name2 ${name2}"
                echo "name3 ${name3}"
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-environment
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] withEnv
[Pipeline] {
[Pipeline] echo
name1 Phyllis
[Pipeline] echo
name2 Likimani
[Pipeline] echo
name3 PhyllisL
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


// if we were to use the same environment variables in a different stage, 
// it will result in a failure because one of the environment variables $name3 
// is only defined in the Build stage, not at the pipeline level, 
// therefore it is not available globally for use in all stages of the pipeline.
// this means that the build stage will be successful but the test step will result in a failure.
pipeline {
    agent any
    environment{
        name1 = "Phyllis"
        name2 = "Likimani"
    }
    stages {
        stage('Build'){
            environment {
                name3 = "PhyllisL"
            }
            steps{
                echo "name1 ${name1}"
                echo "name2 ${name2}"
                echo "name3 ${name3}"
            }
        }
        
        stage('Test'){
            steps{
                echo "name1 ${name1}"
                echo "name2 ${name2}"
                echo "name3 ${name3}"
            }
        }
    }
}

Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-environment
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] withEnv
[Pipeline] {
[Pipeline] echo
name1 Phyllis
[Pipeline] echo
name2 Likimani
[Pipeline] echo
name3 PhyllisL
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Test)
[Pipeline] echo
name1 Phyllis
[Pipeline] echo
name2 Likimani
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
groovy.lang.MissingPropertyException: No such property: name3 for class: groovy.lang.Binding
	at groovy.lang.Binding.getVariable(Binding.java:63)
	at org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.SandboxInterceptor.onGetProperty(SandboxInterceptor.java:266)
	at org.kohsuke.groovy.sandbox.impl.Checker$7.call(Checker.java:375)
	at org.kohsuke.groovy.sandbox.impl.Checker.checkedGetProperty(Checker.java:379)
	at org.kohsuke.groovy.sandbox.impl.Checker.checkedGetProperty(Checker.java:355)
	at org.kohsuke.groovy.sandbox.impl.Checker.checkedGetProperty(Checker.java:355)
	at com.cloudbees.groovy.cps.sandbox.SandboxInvoker.getProperty(SandboxInvoker.java:29)
	at com.cloudbees.groovy.cps.impl.PropertyAccessBlock.rawGet(PropertyAccessBlock.java:20)
	at WorkflowScript.run(WorkflowScript:23)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.delegateAndExecute(ModelInterpreter.groovy:137)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.executeSingleStage(ModelInterpreter.groovy:666)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.catchRequiredContextForNode(ModelInterpreter.groovy:395)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.catchRequiredContextForNode(ModelInterpreter.groovy:393)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.executeSingleStage(ModelInterpreter.groovy:665)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:288)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.toolsBlock(ModelInterpreter.groovy:544)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.toolsBlock(ModelInterpreter.groovy:543)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:276)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.withEnvBlock(ModelInterpreter.groovy:443)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.withEnvBlock(ModelInterpreter.groovy:442)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:275)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.withCredentialsBlock(ModelInterpreter.groovy:481)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.withCredentialsBlock(ModelInterpreter.groovy:480)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:274)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.inDeclarativeAgent(ModelInterpreter.groovy:586)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.inDeclarativeAgent(ModelInterpreter.groovy:585)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:272)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.stageInput(ModelInterpreter.groovy:356)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.stageInput(ModelInterpreter.groovy:355)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:261)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.inWrappers(ModelInterpreter.groovy:618)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.inWrappers(ModelInterpreter.groovy:617)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:259)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.withEnvBlock(ModelInterpreter.groovy:443)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.withEnvBlock(ModelInterpreter.groovy:442)
	at org.jenkinsci.plugins.pipeline.modeldefinition.ModelInterpreter.evaluateStage(ModelInterpreter.groovy:254)
	at ___cps.transform___(Native Method)
	at com.cloudbees.groovy.cps.impl.PropertyishBlock$ContinuationImpl.get(PropertyishBlock.java:73)
	at com.cloudbees.groovy.cps.LValueBlock$GetAdapter.receive(LValueBlock.java:30)
	at com.cloudbees.groovy.cps.impl.PropertyishBlock$ContinuationImpl.fixName(PropertyishBlock.java:65)
	at jdk.internal.reflect.GeneratedMethodAccessor92.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at com.cloudbees.groovy.cps.impl.ContinuationPtr$ContinuationImpl.receive(ContinuationPtr.java:72)
	at com.cloudbees.groovy.cps.impl.ConstantBlock.eval(ConstantBlock.java:21)
	at com.cloudbees.groovy.cps.Next.step(Next.java:83)
	at com.cloudbees.groovy.cps.Continuable$1.call(Continuable.java:152)
	at com.cloudbees.groovy.cps.Continuable$1.call(Continuable.java:146)
	at org.codehaus.groovy.runtime.GroovyCategorySupport$ThreadCategoryInfo.use(GroovyCategorySupport.java:136)
	at org.codehaus.groovy.runtime.GroovyCategorySupport.use(GroovyCategorySupport.java:275)
	at com.cloudbees.groovy.cps.Continuable.run0(Continuable.java:146)
	at org.jenkinsci.plugins.workflow.cps.SandboxContinuable.access$001(SandboxContinuable.java:18)
	at org.jenkinsci.plugins.workflow.cps.SandboxContinuable.run0(SandboxContinuable.java:51)
	at org.jenkinsci.plugins.workflow.cps.CpsThread.runNextChunk(CpsThread.java:187)
	at org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.run(CpsThreadGroup.java:420)
	at org.jenkinsci.plugins.workflow.cps.CpsThreadGroup$2.call(CpsThreadGroup.java:330)
	at org.jenkinsci.plugins.workflow.cps.CpsThreadGroup$2.call(CpsThreadGroup.java:294)
	at org.jenkinsci.plugins.workflow.cps.CpsVmExecutorService$2.call(CpsVmExecutorService.java:67)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at hudson.remoting.SingleLaneExecutorService$1.run(SingleLaneExecutorService.java:139)
	at jenkins.util.ContextResettingExecutorService$1.run(ContextResettingExecutorService.java:30)
	at jenkins.security.ImpersonatingExecutorService$1.run(ImpersonatingExecutorService.java:70)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:829)
Finished: FAILURE


// using different variables with the same name at the pipeline and stage levels:
// when this happens, the variable at the stage level overrides what is present at the pipeline level
pipeline {
    agent any
    environment{
        name1 = "Phyllis"
        name2 = "Likimani"
    }
    stages {
        stage('Build'){
            environment {
                name2 = "PhyllisL" // same name, different variable
                name3 = "Jamie"
            }
            steps{
                echo "name1 ${name1}"
                echo "name2 ${name2}"
                echo "name3 ${name3}"
            }
        }
        
    }
}

// console output:
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-environment
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] withEnv
[Pipeline] {
[Pipeline] echo
name1 Phyllis
[Pipeline] echo
name2 PhyllisL
[Pipeline] echo
name3 Jamie
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


// Jenkins also has some inbuilt environmenetal variables that are available for use in our scripts as needed:
pipeline {
    agent any
    environment{
        name1 = "Phyllis"
        name2 = "Likimani"
    }
    stages {
        stage('Build'){
            environment {
                name2 = "PhyllisL"
                name3 = "Jamie"
            }
            steps{
                echo "name1 ${name1}"
                echo "name2 ${name2}"
                echo "name3 ${name3}"
                sh "printenv"  // used to printout Jenkins environment variables
            }
        }
        
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-environment
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] withEnv
[Pipeline] {
[Pipeline] echo
name1 Phyllis
[Pipeline] echo
name2 PhyllisL
[Pipeline] echo
name3 Jamie
[Pipeline] sh
+ printenv
JENKINS_HOME=/var/lib/jenkins
USER=jenkins
CI=true
name1=Phyllis
RUN_CHANGES_DISPLAY_URL=http://54.183.217.240:8080/job/pipeline-stage-environment/4/display/redirect?page=changes
name2=PhyllisL
NODE_LABELS=built-in
HUDSON_URL=http://54.183.217.240:8080/
name3=Jamie
HOME=/var/lib/jenkins
BUILD_URL=http://54.183.217.240:8080/job/pipeline-stage-environment/4/
HUDSON_COOKIE=bb646158-ce9c-49fa-b372-1ce44f8aaf45
JENKINS_SERVER_COOKIE=durable-07357edde9fced9c2387ea80024e258bd5ad8074456895b48b51c56ac0535f8e
NOTIFY_SOCKET=/run/systemd/notify
SYSTEMD_EXEC_PID=460
WORKSPACE=/var/lib/jenkins/workspace/pipeline-stage-environment
LOGNAME=jenkins
NODE_NAME=built-in
JOURNAL_STREAM=8:16308
RUN_ARTIFACTS_DISPLAY_URL=http://54.183.217.240:8080/job/pipeline-stage-environment/4/display/redirect?page=artifacts
STAGE_NAME=Build
EXECUTOR_NUMBER=1
RUN_TESTS_DISPLAY_URL=http://54.183.217.240:8080/job/pipeline-stage-environment/4/display/redirect?page=tests
BUILD_DISPLAY_NAME=#4
HUDSON_HOME=/var/lib/jenkins
JOB_BASE_NAME=pipeline-stage-environment
PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/snap/bin
INVOCATION_ID=394006f8b440498283d9c09bd88be8d4
BUILD_ID=4
BUILD_TAG=jenkins-pipeline-stage-environment-4
LANG=C.UTF-8
JENKINS_URL=http://54.183.217.240:8080/
JOB_URL=http://54.183.217.240:8080/job/pipeline-stage-environment/
BUILD_NUMBER=4
JENKINS_NODE_COOKIE=584f3207-eb6b-4965-a9ef-d0c25d70fe84
SHELL=/bin/bash
RUN_DISPLAY_URL=http://54.183.217.240:8080/job/pipeline-stage-environment/4/display/redirect
HUDSON_SERVER_COOKIE=7890ef17a18dbd9a
JOB_DISPLAY_URL=http://54.183.217.240:8080/job/pipeline-stage-environment/display/redirect
JOB_NAME=pipeline-stage-environment
PWD=/var/lib/jenkins/workspace/pipeline-stage-environment
WORKSPACE_TMP=/var/lib/jenkins/workspace/pipeline-stage-environment@tmp
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS


credentials()
// credentials allows us to retrieve credentials previously created in Jenkins are referred to by its ID
// dashboard > manage Jenkins > credentials > system > global credentials (unrestricted) > add new credentials
// Jenkins will print out the values as asteriks to avoid exposing sensitive information.
pipeline {
    agent any
    
    stages {
        stage('Build') {
            environment{
                SOME_USERNAME_PASSWORD = credentials('some_username_password')
                SOME_SECRET = credentials('some_secret')
            }
            steps{
                echo "SOME_USERNAME_PASSWORD ${SOME_USERNAME_PASSWORD}" //SOME_USERNAME_PASSWORD is in the format of username:password
                echo "SOME_USERNAME_PASSWORD_USR ${SOME_USERNAME_PASSWORD_USR}"
                echo "SOME_USERNAME_PASSWORD_PSW ${SOME_USERNAME_PASSWORD_PSW}"
                echo "SOME_SECRET ${SOME_SECRET}"
            }
        }    
    }
}

//console output: 
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/pipeline-stage-environment
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] withCredentials
Masking supported pattern matches of $SOME_USERNAME_PASSWORD or $SOME_USERNAME_PASSWORD_PSW or $SOME_SECRET
[Pipeline] {
[Pipeline] echo
Warning: A secret was passed to "echo" using Groovy String interpolation, which is insecure.
		 Affected argument(s) used the following variable(s): [SOME_USERNAME_PASSWORD_PSW, SOME_USERNAME_PASSWORD, SOME_SECRET]
		 See https://jenkins.io/redirect/groovy-string-interpolation for details.
SOME_USERNAME_PASSWORD ****
[Pipeline] echo
SOME_USERNAME_PASSWORD_USR username
[Pipeline] echo
Warning: A secret was passed to "echo" using Groovy String interpolation, which is insecure.
		 Affected argument(s) used the following variable(s): [SOME_USERNAME_PASSWORD_PSW, SOME_SECRET]
		 See https://jenkins.io/redirect/groovy-string-interpolation for details.
SOME_USERNAME_PASSWORD_PSW ****
[Pipeline] echo
Warning: A secret was passed to "echo" using Groovy String interpolation, which is insecure.
		 Affected argument(s) used the following variable(s): [SOME_USERNAME_PASSWORD_PSW, SOME_SECRET]
		 See https://jenkins.io/redirect/groovy-string-interpolation for details.
SOME_SECRET ****
[Pipeline] }
[Pipeline] // withCredentials
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
// 	The following steps that have been detected may have insecure interpolation of sensitive variables:
        // echo: [SOME_USERNAME_PASSWORD_PSW, SOME_USERNAME_PASSWORD, SOME_SECRET]
        // echo: [SOME_USERNAME_PASSWORD_PSW, SOME_SECRET]
        // echo: [SOME_USERNAME_PASSWORD_PSW, SOME_SECRET]
//ref: https://www.jenkins.io/doc/book/pipeline/jenkinsfile/#string-interpolation

