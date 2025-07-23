pipeline {
    //agent any
    //agent { label 'maven' }
    //agent { label '! without-maven' }
    agent {
        docker { image 'maven:3.9-amazoncorretto-21-debian' }
     }
	environment{
	    //NB: credential_dockerhub_didierdefrance69 is ID of credential
		//prepared in "Admin Jenkins / Credentials / system /global"
		dockerhub_credential_id='credential_dockerhub_didierdefrance69'
		
		//dockerRegistry is dockerhub
		docker_registry= 'https://registry.hub.docker.com'
		
		docker_image_name='didierdefrance69/appli_spring_v3:1'
	}
    stages {
	    //stage('from_git') {
        //    steps {
        //          git url : 'https://github.com/didier-tp/spring_2025' , branch : 'main'
        //    }
        //}
        stage('mvn_clean_package_skip_test') {
            steps {
			script {
			dir('tp/mysecurity-autoconfigure') {
					echo 'mvn clean install mysecurity-autoconfigure (dependency) '
					sh 'mvn clean install -Dmaven.test.skip=true'
					}
				}
              dir('tp/appliSpringWeb') {
					echo 'mvn clean package '
					sh 'mvn clean package -Dmaven.test.skip=true'
					}
				}
            }
        }
		stage('mvn test') {
            steps {
			 script {
              dir('tp/appliSpringWeb') {
					echo 'mvn test'
					sh 'mvn test'
					}
				}
            }
        }
		stage('build_docker_image') {
			steps {
			 script{
			   dir('tp/appliSpringWeb') {
				    echo "docker_image_name=" + docker_image_name
					//dockerImage = docker.build(docker_image_name)
				    }
				  }
			   }
        }
		stage('push_docker_image') {
            steps {
			  script{
			   dir('tp/appliSpringWeb') {
					echo "docker_registry=" + docker_registry
					echo "dockerhub_credential_id=" +dockerhub_credential_id
					//docker.withRegistry( docker_registry, dockerhub_credential_id ) { 
					//     dockerImage.push() 
					//	 }
					  }
					}
				  }
		}
    }
}
