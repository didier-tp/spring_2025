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

=======
démarrer container docker depuis l'image:
docker run -d -p 8181:8181 --name applispring-container tp/applispring


