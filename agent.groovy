// options available under agent:
// 1. any: any informs Jenkins to run on any available agent
// 2. none:when applied at the top-level of the pipeline block no global agent will be applied for the entire pipeline run
// and each stage section will need to contain its own agent section.
// it informs Jenkins there is no global agent and if there are no agents specified at the state level, then it runs on current Jenkins.
// 3. label: A string on which to run the pipeline or individual stage. 
// executes the pipeline or stage or an agent available in the jenkins environment with the provided label.
// this option is available for node, docker and dockerfile and is required for node.
// It tells Jenkins to run a script in a particular agent


//label: use the label given to the node you want the job to run on in the master-slave architecture. This can be found under:
// manage Jenkins > manage nodes and clouds
// so if the lable of the node to run the job is slave 1, the pipeline can be configured like this:
pipeline{
    agent {
        label "slave 1"
    }
    stages{
        stage("Build"){
            steps{
                echo "Hello world!"
            }
        }
    }
}

on node:
pipeline{
    agent {
        node {
            label "slave 1"
            customWorkspace "/home/ec2-user/customWorkspace" //<== this customWorkspace would have to have been create on the slave server site
        }
    }
    stages{
        stage("Build"){
            steps{
                echo "Hello world!"
            }
        }
    }
}