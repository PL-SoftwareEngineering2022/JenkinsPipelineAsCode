// tools: a section defining tools to auto install and put on the PATH. This is ignored if agent none is specified 
// tools can be added under stage or under pipeline
// the tool name must be pre-configured in Jenkins under:
        // Manage Jenkins > Global Tool Configuration
// this section refers to preinstalled tools in the global tool configuration and makes the tools available for use in a pipeline script.

pipeline{
    agent any
    
    stages {
        stage('Build') {
            tools {
                maven 'maven'
            }
            steps {
                sh 'mvn --version' // sh will execute a shell script
            }
        }
    }
}

// console output
Started by user phyllis likimani
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/tools
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] tool
Unpacking https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.6.3/apache-maven-3.6.3-bin.zip to /var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven on Jenkins
[Pipeline] envVarsForTool
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ mvn --version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven
Java version: 11.0.17, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.15.0-1026-aws", arch: "amd64", family: "unix"
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS

