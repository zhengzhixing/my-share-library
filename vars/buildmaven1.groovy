def call(Map parameters = [:]) {
    pipeline {
        agent any
        environment {
            def project = parameters.project
            def config = readYaml text: libraryResource('org/zzx/project.yml')
            def git_url = config.projects."$project".git_url
        }
        stages {
            stage('构建'){
                steps {
                    git url: "$git_url"
                    sh "mvn install"
                }
            }
            stage('测试'){
                steps {
                    echo "正在测试"
                    sh 'mvn test'
                }
            }
            stage('发布'){
                steps {
                    echo "正在发布"
                    sh 'sh /var/lib/jenkins/tomcat-jdk.sh'
                }
            }
    
        }
    }
}
