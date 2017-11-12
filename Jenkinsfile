pipeline {
  agent any
  
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
      }
    }
  }
}
