set JAVA_HOME=C:\Prog\java\open-jdk\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
echo %PATH%
echo "******************************"
echo "via config/application.properties externe au .jar , :8282 a la place de :8181"
echo "http://localhost:8282/sbapp"
echo "******************************"
java -Dspring.profiles.active=dev_h2,ddl_auto -jar target/sbapp-0.0.1-SNAPSHOT.jar

pause