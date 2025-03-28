git clone https://github.com/didier-tp/spring_2025
==========
sudo apt-get -y install openjdk-21-jdk
java -version
=========
installe maven compatible
====================
se place dans le répertoire du projet tp/mars2025/debutApplispringWeb
mnv package -DskipTests
====
target/debutApplispringWeb.jar

====
essai sans docker:
java -jar ./target/debutAppliSpringWeb.jar
======
pour construire image docker (via le fichier Dockerfile à la racine du projet)
docker build -t tp/applispring .

=====
pour construire image docker via maven et springBoot :
mvn spring-boot:build-image -DskipTests

=======
démarrer container docker depuis l'image:
docker run -d -p 8181:8181 --name applispring-container tp/applispring
docker container ls
http://localhost:8181/appliSpring dans navigateur
curl http://localhost:8181/appliSpring
----
docker stop applispring-container
docker container ls -a
docker rm applispring-container
docker container ls -a

===========
NB: utiliser docker compose si besoin de démarrer container MySQL ou postgres en plus ….



