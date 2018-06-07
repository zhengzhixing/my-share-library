def config = readYaml text: libraryResource('org/zzx/project.yml')
def call(Map parameters = [:]) {
    environment {
        def project = parameters.project
        def git_url = config.projects."$project".git_url
    }
    pipeline {
        agent any
        stages {
            stage('Build'){
                steps {
                    git url: "git://github.com:zhengzhixing/"${git_url}", branch: 'master'
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
