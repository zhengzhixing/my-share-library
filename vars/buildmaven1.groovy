         def  project = parameters.project
         def  config = readYaml yml: libraryResource('org/zzx/project.yml')
         def  git = config.projects."$project".git_url 

def call(Map parameters = [:]) {
    environment {
         def  project = parameters.project
         def  config = readYaml yml: libraryResource('org/zzx/project.yml')
         def  git = config.projects."$project".git_url 
    }
    pipeline {
        agent any
        stages {
            stage('Build'){
                steps {
                       git url: "${git}", branch: 'master'
                       sh "mvn install"
                }
            }
            stage('Test'){
                steps {
                    echo "正在测试"
                    sh 'mvn test'
                }
            }
            stage('deplou'){
                steps {
                    echo "正在发布"
                    sh 'sh /var/lib/jenkins/tomcat-jdk.sh'
                }
            }
    
        }
    }
}
