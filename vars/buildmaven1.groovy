def call(Map parameters = [:]) {
    pipeline {
        agent any
        stages {
            stage('Build'){
                steps {
                    script {
                       yaml = libraryResource 'org/zzx/project.yml'
                        git_url = "${yaml.projects.${parameters}.name.git_url}"
                        git url: "${git_url}"
                       sh "mvn install"
                    }
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
