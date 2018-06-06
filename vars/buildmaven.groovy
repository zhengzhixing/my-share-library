def call(Map git) {
    pipeline {
         angent any
         stages {
              stage('Build'){
                   echo "正在构建"
                   git url: "git@github.com:zhengzhixing/${git.name}-life.git"
                   sh "mvn install"
              }
              stage('Test'){
                   echo "正在测试"
                   sh 'mvn test'
              }
              stage('Deploy'){
                   echo "正在发布"
                   sh 'sh /root/tomcat-jdk.sh'
              }
         }
    }
}
