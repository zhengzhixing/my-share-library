def config = readYaml text: libraryResource('org/zzx/project.yml')
def call(Map parameters = [:]) {
pipeline {
    agent any
    environment { 
        def project = parameters.project
        def git_url = config.projects."$project".git_url
    }
        stages {
              stage('Build'){
                  steps{
                       echo "正在构建1"
                      print "${git_url}"
                       git url: "${git_url}", branch: 'master'
                       sh "mvn install"
                  }
              }
              stage('Test'){
                   steps {
                       echo "正在测试"
                       sh 'mvn test'
                   }
              }
              stage('Deploy'){
                   steps{
                       echo "正在发布"
                       sh 'sh /var/lib/jenkins/tomcat-jdk.sh'
                   }
              }
         }
    }
}
