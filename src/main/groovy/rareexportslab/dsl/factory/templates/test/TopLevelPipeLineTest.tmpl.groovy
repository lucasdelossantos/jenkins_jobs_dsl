package rareexportslab.dsl.factory.templates.test

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