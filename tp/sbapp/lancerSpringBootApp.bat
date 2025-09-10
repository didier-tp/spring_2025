set JAVA_HOME=C:\Prog\java\open-jdk\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
echo %PATH%
java -Dspring.profiles.active=dev_h2,ddl_auto -jar target/sbapp-0.0.1-SNAPSHOT.jar
pause