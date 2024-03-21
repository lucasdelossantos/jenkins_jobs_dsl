pipelineJob('docker-test') {
    description('A simple demo for Jenkins DSL')
    definition {
        cps{
        sandbox()
        script('''
            pipeline {
                agent {
                    docker {
                        image 'node:20.11.1-alpine3.19'
                        args '-v /var/run/docker.sock:/var/run/docker.sock'
                    }
                }
                stages {
                    stage('Test') {
                        steps {
                            sh 'npm --version'
                            sh 'node --version'
                        }
                    }
                }
            }     
        '''.stripIndent())
        }
    }
}

