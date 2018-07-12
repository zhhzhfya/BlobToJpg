@echo off & setlocal enabledelayedexpansion

set BASE=%~dp0
set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin
set CLASSES=%BASE%target\test-classes
if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx

echo %CLASSES%;..\conf;%LIB_JARS%

java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath ..\target\test-classes;..\conf;%LIB_JARS% com.appsoft.data_trans.MainFrame
goto end

:debug
java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -classpath %CLASSES%;..\conf;%LIB_JARS% com.appsoft.data_trans.MainFrame
goto end

:jmx
java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -classpath %CLASSES%;..\conf;%LIB_JARS% com.appsoft.data_trans.MainFrame

:end
pause