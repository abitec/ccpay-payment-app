#!groovy

properties(
    [[$class: 'GithubProjectProperty', displayName: 'Payment API', projectUrlStr: 'http://git.reform/common-components/payment-app/'],
    pipelineTriggers([[$class: 'GitHubPushTrigger']])]
)

node {
    try {
        configure(env)
        withWorkspace(cleanup: false) {
            stage('Checkout') {
                checkout scm
            }

            stage('Build (JAR)') {
                sh '''
                    mvn clean verify
                '''
                archiveArtifacts 'api/target/*.jar'
            }

            stage('Build (Docker)') {
                sh '''
                    docker-compose build --force-rm
                '''
            }
        }
    } catch (err) {
         slackSend(
             channel: '#cc_tech',
             color: 'danger',
             message: "${env.JOB_NAME}: <${env.BUILD_URL}console|Build ${env.BUILD_DISPLAY_NAME}> has FAILED")
         throw err
     }
}

private void configure(env) {
    def mvnHome = tool 'apache-maven-3.3.9'
    env.PATH = "${mvnHome}/bin:${env.PATH}"
    env.MAVEN_OPTS = "${env.MAVEN_OPTS != null ? env.MAVEN_OPTS : ''} ${proxySystemProperties(env)}"
}

private proxySystemProperties(env) {
    def systemProperties = []
    if (env.http_proxy != null) {
        def proxyURL = new URL(env.http_proxy)
        systemProperties.add("-Dhttp.proxyHost=${proxyURL.getHost()}")
        systemProperties.add("-Dhttp.proxyPort=${proxyURL.getPort()}")
    }
    if (env.https_proxy != null) {
        def proxyURL = new URL(env.https_proxy)
        systemProperties.add("-Dhttps.proxyHost=${proxyURL.getHost()}")
        systemProperties.add("-Dhttps.proxyPort=${proxyURL.getPort()}")
    }
    if (env.no_proxy != null) {
        systemProperties.add("-Dhttp.nonProxyHosts=${env.no_proxy}")
    }
    return systemProperties.join(' ')
}

private void withWorkspace(Map args = [cleanup: true], Closure closure) {
    ws('workspace/cc-payment-api/build') {
        try {
            closure()
        } finally {
            if (args.cleanup) {
                deleteDir()
            }
        }
    }
}