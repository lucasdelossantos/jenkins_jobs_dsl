pipelineJob('Jenkins Tutorial Demo - Simple DSL job') {
    description('A simple demo for Jenkins DSL')
    parameters {
        stringParam('Test', 'Test,', '')
    }
    definition {
        cps{
        sandbox()
        script('''
            pipeline {
                agent any
                stages {
                    stage('Stage 1') {
                        steps {
                         echo 'logic'
                        }
                    }
                }
            }     
        '''.stripIndent())
        }
    }
}