package rareexportslab.dsl.factory.test

import groovy.transform.InheritConstructors
import rareexportslab.dsl.factory.JobFactory

@InheritConstructors
public class Test extends JobFactory {

    def topLevelPipelineTest(Map options = [:]){
        def job = pipelineJobFromFile('Top-Level-Pipeline-Test',
        'Builds multiple triggers from single job',
        'factory/templates/test/TopLevelPipeLineTest.tmpl.groovy'
        )
        job.with({
            logRotator(30, 200, -1, 0)
        })
    }
}