@echo off
set BASEDIR=%~sdp0
call %BASEDIR%\setEnv.bat

echo removing the old
rmdir /S /Q %TOMCAT_HOME%\webapps\apms >NUL 2>NUL
del /F /Q %TOMCAT_HOME%\webapps\apms.war >NUL
echo deploying the new
copy /Y %PROJECT_HOME%\apms-build\apms-war\target\apms.war %TOMCAT_HOME%\webapps\apms.war
echo done
timeout /t 1 >NUL