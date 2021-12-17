package rareexportslab.dsl.factory

import groovy.text.StreamingTemplateEngine

class JobFactory {
    def _dslFactory
    JobFactory(dslFactory){
        _dslFactory = dslFactory
    }
    def myJob(String _jobName, String _description, Map options=[:]) {
        return _dslFactory.freeStyleJob(_jobName, options) {
            description "CONFIG MANAGED - ${_description}"
        }
    }

    def myMavenJob(String _jobName, String _description, Map options=[:]) {
        return _dslFactory.mavenJob(_jobName, options) {
            description "CONFIG MANAGED - ${_description}"
        }
    }


    def myPipelineJob(String _jobName, String _description, Map options=[:]) {
        return _dslFactory.pipelineJob(_jobName, options) {
            description "CONFIG MANAGED - ${_description}"
        }
    }

    /* Generates a job from a groovy file
    * Must be stored in the relative path: rareexportslab/dsl/factory/...
    * Uses Groovy's streaming template engine to insert variable data via the bindings map */
    def pipelineJobFromFile(String _jobName, String _description, String relativePath, Map options=[:], Map bindings=[:]) {
        def job = myPipelineJob(_jobName, _description, options)
        final String fullPath = String.format("rareexportslab/dsl/%s", relativePath)
        job.with({
            definition {
                cps {
                    sandbox true
                    script """${this.templateGroovyFile(fullPath, bindings)}""".stripIndent()
                }
            }
        })
        return job
    }

    /* Helper method for pipelineJobFromFile
    * Takes a groovy file and uses Groovy's template engine to evaluate values and other code snippets */
    String templateGroovyFile(final String groovyFile, final Map bindings = [:]) {
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(groovyFile)
        if(inputStream == null) {
            throw new FileNotFoundException(String.format('Was unable to find file %s, please check the path!', groovyFile))
        }
        def reader = new InputStreamReader(inputStream)
        final String replacedContents = reader.getText().replace('${', '\\${')
        println(replacedContents)
        def template = new StreamingTemplateEngine().createTemplate(replacedContents)
        inputStream.close()
        reader.close()
        return template.make(bindings)
    }


    static def mergeDefaultOptions(Map defaultOptions, Map options) {
        defaultOptions.each{ k, v ->
            if(options[k] == null) {
                options[k] = v
            }
        }
        return options
    }
}