def project = parameters.project
def config = readYaml text: libraryResource('org/zzx/project.yml')

def call(Map parameters = [:]) {
    git_url = config.projects."$parameters".git_url
    pipeline {
         agent any
         stages {
              stage('Build'){
                  steps{
                       echo "正在构建1"
                       git url: "${git_url}"
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
