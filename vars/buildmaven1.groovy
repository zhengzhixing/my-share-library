            def project = parameters.project
            def config = readYaml text: libraryResource('org/zzx/project.yml')
            def git_url = config.projects."$project".git_url
                    println "$project"
                    println "$git_url"
def call(Map parameters = [:]) {
        environment {

        }
    pipeline {
        agent any
        stages {
            stage('构建'){
                steps {
                    println "$project"
                    println "$git_url"
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
