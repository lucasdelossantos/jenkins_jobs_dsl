package rareexportslab.dsl.factory

import rareexportslab.*
import groovy.text.StreamingTemplateEngine

public class JobFactory {
    def _dslFactory
    JobFactory(dslFactory){
        _dslFactory = dslFactory
    }
    def myJob(String _name, String _description) {
        return _dslFactory.freeStyleJob(_name) {
            description "CONFIG MANAGED - ${_description}"
        }
    }

    def myPipelineJob(String _name, String _description) {
        return _dslFactory.pipelineJob(_name) {
            description "CONFIG MANAGED - ${_description}"
        }
    }
}