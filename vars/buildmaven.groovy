def call(Map git) {
    pipeline {
         agent any
         stages {
              stage('Build'){
                  steps{
                       echo "正在构建"
                       git url: "git@github.com:zhengzhixing/${git.name}-life.git"
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
                       sh 'sh /root/tomcat-jdk.sh'
                   }
              }
         }
    }
}
