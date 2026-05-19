#!groovy

def workerNode = "devel12"

pipeline {
	agent {label workerNode}
	tools {
		jdk 'jdk11'
		maven 'Maven 3'
	}
	options {
		timestamps()
	}
	environment {
        SONAR_SCANNER_HOME = tool 'SonarQube Scanner from Maven Central'
        SONAR_SCANNER = "$SONAR_SCANNER_HOME/bin/sonar-scanner"
        SONAR_PROJECT_KEY = "openformat-connector"
        SONAR_SOURCES = "src"
        SONAR_TESTS = "test"
    }
	stages {
		stage("clear workspace") {
			steps {
				deleteDir()
				checkout scm
			}
		}
		stage("build") {
			steps {
			    sh "mvn -T 4 verify pmd:pmd pmd:cpd spotbugs:spotbugs"
				junit "target/surefire-reports/TEST-*.xml"
			}
		}
		stage("sonarqube") {
            steps {
                ansiColor('xterm') {
                    withSonarQubeEnv(installationName: 'sonarqube.dbc.dk') {
                        script {
                            def status = 0

                            def sonarOptions = "-Dsonar.branch.name=${BRANCH_NAME}"
                            if (env.BRANCH_NAME != 'main') {
                                sonarOptions += " -Dsonar.newCode.referenceBranch=master"
                            }

                            // Do sonar via maven
                            status += sh returnStatus: true, script: """
                                mvn -B -Dmaven.repo.local=$WORKSPACE/.repo --no-transfer-progress $sonarOptions sonar:sonar
                            """

                            if (status != 0) {
                                error("build failed")
                            }
                        }
                    }
                }
            }
        }
		stage("warnings") {
			agent {label workerNode}
			steps {
				warnings consoleParsers: [
					[parserName: "Java Compiler (javac)"]
				],
					unstableTotalAll: "0",
					failedTotalAll: "0"
			}
		}
		stage("pmd") {
			agent {label workerNode}
			steps {
				step([$class: 'hudson.plugins.pmd.PmdPublisher',
					  pattern: 'target/pmd.xml',
					  unstableTotalAll: "0",
					  failedTotalAll: "0"])
			}
		}
		stage("quality gate") {
            steps {
                // wait for analysis results
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
		stage("deploy") {
			when {
				branch "master"
			}
			steps {
				sh "mvn jar:jar deploy:deploy"
			}
		}
	}
}
