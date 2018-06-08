                 config = readYaml text: libraryResource("org/zzx/project.yml")                
                 git_url = "$config".projects.gameoflife-web.git_url
def call(Map parameters = 'gameoflife-web') {
pipeline {
    agent any
        stages {
              stage('Build'){
                  steps{
                       echo "正在构建1"
                    git url: "git@github.com:zhengzhixing/${git_url}.git" 
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
