pipeline {
  agent any
  
  environment {
    OS_VERSION="Centos72"
  }
  
  stages{
    stage('Prepare') {
      steps {
        echo "Hello World - Prepare"
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/rudyk88/jenkins-pipelines.git']]])
        sh 'chmod 700 *.sh'
        sh './prepare.sh'
      }
    }
  
    stage ('Process') {
      steps {
        echo "Hello World - Process"
        sh './process.sh'
      }
    }
  
    stage ('Publish') {
      steps {
        echo "Hello World - Publish"
        sh '''
          target_dir="target"
          fileName="members_list.txt"
          if [ -f ${target_dir}/${fileName} ]; then
            cat ${target_dir}/${fileName}
          fi
        '''
        archiveArtifacts artifacts: 'target/*.txt'
        
        stash name: "first-stash", includes: "target/*"
      }
    }
    
    stage ('Parallel-Steps') {
      parallel {
        stage ('Parallel Step 1') {
          steps {
            echo "This is parallel step 1"
          }
        }
        stage ('Parallel Step 2') {
          steps {
            echo "This is parallel step 2"
          }
        }
      }
    }
    
    stage ('test-post-publish') {
      steps {
        echo "Running Test Post Publish"
        dir('first-stash-directory') {
          unstash "first-stash"
          sh 'cat target/*.txt'
        }
      }
    }
  }
  
  post {
    always {
      echo "This is a post build step that will always run"
      echo "Environment variable is ${env.OS_VERSION}"
    }
  }
}
