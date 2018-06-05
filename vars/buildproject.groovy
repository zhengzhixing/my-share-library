def call(Map config) {
    node {
        git url: "git@github.com:zhengzhixing/game-of-life.git"
        sh 'mvn install'
        mail to: '...', subject: "${config.name} plugin build", body: '...'
    }
}
