// changeset condition only allows the stage to execute when the files that were committed have file names 
// that meet a certain regular expression pattern.

// this will execute the stage if there are files that end in .js / javascript files
pipeline {
    agent any
    stages{
        stage('Build'){
            when {
                changeset glob:"*.js"
            }
            steps {
                echo "hello world changeset JS"
            }
        }
    }
}

// you can also specify case-sensitivity using the "caseSensitive" field:
// this will look for filenames that end in the uppercase "WORLD" and the extension ".js"
pipeline {
    agent any
    stages{
        stage('Build'){
            when {
                changeset glob:"WORLD.js", caseSensitive: true
            }
            steps {
                echo "hello world changeset JS"
            }
        }
    }
}