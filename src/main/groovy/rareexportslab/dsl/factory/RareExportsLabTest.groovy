package rareexportslab.dsl.factory

import rareexportslab.dsl.factory.JobFactory

public class RareExportsLabTest extends JobFactory {

    RareExportsLabTest(Object dslFactory) {
        super(dslFactory)
    }

    def testJob() {
        def job = myPipelineJob('Test-Job', 'Initial Test job')

        job.with({
            definition {
                cps {
                    sandbox true
                    script """\
                    pipeline {
                        agent any
                        stages {
                            stage('Hello') {
                                steps {
                                    echo 'Hello World'
                                }
                            }
                            stage('first') {
                                steps {
                                    script {
                                        trigger("test-first-trigger")
                                    }
                                }
                            }
                            stage('second') {
                                steps {
                                    script {
                                        trigger("test-second-trigger")
                                    }
                                }
                            }
                        }
                    }
                    """.stripIndent()
                }
            }
        })
        return job
    }
}
