in4rows
=======

Implementation of the 4 row game in Java.
This is a little school project that maybe will continue.
There is a server side program and have to be added a console command line.

0 - Download 

The project is freely downloadable under Github at:
https://github.com/samouille666/in4rows

1 - Installation

	1.1 - Eclipse 
		Under Eclipse it is sufficient to download the project or copy it in the workspace of Eclipse and create a project of exactly the same name for eclipse to create .project and .classpath automatically.
	
	1.2 - Plug-ins
		The project use 2 plug-ins: 
			
			- M2Eclipse: the standard of the plug-in that allow the use of Maven to handle the dependencies of the project. If you wish to use Maven, the project contain a pom.xml that us sufficient to import all the dependencies of the project automatically.
			If using M2Eclipse, it's necessary to give the project a Maven nature (Right click > Maven > Add Maven nature
			
			- AJDT: the standard plug-in to handle AspectJ code.
	
	1.3 - Dependency libraries
		- JUnit 4.11: The testing framework 
		- AspectJ (Runtime) 1.7.4: The aspect framework
		- Spring-context 4.0.0: injection dependency container framework
	
	If Maven is not used it is possible to add the dependencies the classic way by adding all the jar in the /dep directory as dependencies of the project.
	
	