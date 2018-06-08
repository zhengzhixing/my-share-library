def call(Map parameters = [:]) {
def request = libraryResource 'org/zzx/project.yml'

pipeline {
    agent any
        stages {
              stage('Build'){
                  steps{
                       echo "正在构建1"
                    git url: "git@github.com:zhengzhixing/${parameters.name}.git" 
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
