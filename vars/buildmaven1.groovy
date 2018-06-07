def call(Map parameters = [:]) {
    environment {
        def project = parameters.name
        def config = readYaml text: libraryResource('org/zzx/project.yml')
        def url = config.projects."$project".git_url
    }
pipeline {
    agent any
    stages {
        stage('构建'){
             steps {
                 print "${url}"
                 git url: "${url}"
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
