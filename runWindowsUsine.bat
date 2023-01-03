@echo off

javac @compile.list

cd bin\src
start javaw Controleur
pause

@echo on