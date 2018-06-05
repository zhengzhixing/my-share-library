def call(Map config) {
    node {
        git url: "git@github.com:zhengzhixing/${config.name}-life.git"
        sh 'mvn install'
    }
}
