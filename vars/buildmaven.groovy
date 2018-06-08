def call(Map parameters = [:]) {
    def yaml = readYaml file: "org/zzx/project.yml"
    def git_url = yaml.projects."${parameters}".name.git_url
pipeline {
    agent any
        stages {
              stage('Build'){
                  steps{
                      echo "${paramaters.name}正在构建1"
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
