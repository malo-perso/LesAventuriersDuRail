#!/bin/bash

javac @compile.list

cd bin/Controleur
nohup java src.Controleur&

sleep 1
exit