def call(String url = 'git@github.com:zhengzhixing/game-of-life.git') {
    checkout([$class: 'GitSCM',branches: [[name: '* / master']],
              userRemoteConfigs: [[credentialsId: '4773d545-8a70-4245-bb89-d576be82414f', url: '"${url}"']]])
}

