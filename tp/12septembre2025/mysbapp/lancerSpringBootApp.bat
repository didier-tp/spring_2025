set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
echo %PATH%
java -Dspring.profiles.active=dev_h2,ddl_auto -jar target/mysbapp-0.0.1-SNAPSHOT.jar
pause