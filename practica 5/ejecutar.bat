@echo off
echo Compilando el proyecto...
javac Clase\*.java SistemaContratacion.java
echo.
echo Ejecutando la aplicacion...
java -cp .;Clase SistemaContratacion
pause

