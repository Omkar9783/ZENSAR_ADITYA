@echo off
REM Navigate to the directory where this file is located
cd /d "%~dp0"

REM Compile and Run the DBViewer
REM We assume java and javac are in the system PATH
echo Compiling...
javac -cp ".;lib/mysql-connector-j-8.4.0.jar" DBViewer.java

echo Starting DBViewer...
java -cp ".;lib/mysql-connector-j-8.4.0.jar" DBViewer

pause
