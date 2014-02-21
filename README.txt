in4rows
=======

Implementation of the 4 row game in Java.
This is a little school project that maybe will continue.
There is a server side program and have to be added a console command line.

Index of terms:
	ROOT - root of the current project once its installation is done


============================================================================
Table of content:

	0 - Download
	1 - Installation & running
	2 - Design and patterns
============================================================================

0 - Download 
-----------------

The project is freely downloadable under Github at:
https://github.com/samouille666/in4rows

1 - Installation
-----------------

	1.1 - Eclipse 
		Under Eclipse it is sufficient to download the project or copy it in the workspace of Eclipse and create a project of exactly the same name for eclipse to create .project and .classpath automatically.
	
	1.2 - Plug-ins
		The project uses plug-ins: 
			
			- M2Eclipse: the standard of the plug-in that allow the use of Maven to handle the dependencies of the project. If you wish to use Maven, the project contain a pom.xml that us sufficient to import all the dependencies of the project automatically.
			If using M2Eclipse, it's necessary to give the project a Maven nature (Right click > Maven > Add Maven nature.
			Advantages of Maven is that is automatise many of building project task such as documentation site with javadoc available for instance.
			see: ROOT/in4rows/target/site/index.html
			
			- AJDT: the standard plug-in to handle AspectJ code.
			
			- UML: uml diagrams files may be directly visualize with the plug-in Fuzz Box
	
	1.3 - Dependency libraries
		- JUnit 4.11: The testing framework 
		- AspectJ (Runtime) 1.7.4: The aspect framework
		- Spring-context 4.0.0: injection dependency container framework
	
		If Maven is not used it is possible to add the dependencies the classic way by adding all the jar in the /dep directory as dependencies of the project.
	
	1.4 - Running
		The main console client of the game is:
		/in4rows/src/in4rows/client/console/ConsoleClient.java
		It has to be run as a Java application but necessitates aspectjrt, spring jars. See dependencies above.
	
2 - Design and pattern
------------------------
		The following principle have governs the coding:
			- code interface and not concrete class
			- favours composition over inheritance
			- give classes one responsibility
			- dependency injection has been used where proved useful

		2.1 - Players (Patterns: Decorator, Strategy, Observer)		
		
			See: ROOT/uml/model-player.png (or uml.pdf)
			
			A general interface that describe a common player behaviour gives birth to three branches that fix:
				- the basic concrete player (id, type...) fits to human players.
				- the player in game situation which is a specialization of the basic player such as it adds its colour and turn. The reason is that a player may be involved potentially in many games added to the fact that colour and turn have meaning while playing and do not outside a game. The pattern used is therefore a Decorator as we don't want to duplicate the basic player information. 
				- the computer player that is a bit specific for the reason that we want it to deduce move alone and therefore is client of a Strategy pattern that give it a potential wide bunch of possible algorithm to deduce the next mode to play (see game strategy part). It may be also encrust to the Decorator player in game situation like its human counterpart and eventually it is also a game Observer client that may be updated with Game Events.  
		
			See: ROOT/uml/strategy-game.png (or uml.pdf)
			
			A Computer player is able to compose any game strategy and implement the eponymous pattern. In the diagram the Player is the client of the strategy pattern. This allows to potentially real time changing the behaviour of the computer opponent.
			
		2.2 - Game (Patterns: Decorator, Observer)
		
			See: ROOT/uml/model-game.png (or uml.pdf)
			
			The game interface sum up characteristics of an instance of a particular game. A slight detail lies in the fact that we may want to manipulate it as a read-only/write-only/read-write game and the corresponding interface have been created.
			Added to this, we want some class such as players, controllers, other potential viewers of the game to be able to be updated with new applied moves. This is implemented as an Observer. But as a game may exist (and tested) separately the observable behaviour is added as a Decorator and delegate to the game class all the game related methods.
			Other classes such as Vertex interface and Move interface have been added to the model for sake of completeness but are not implementing any particular pattern.
		
		2.3 - Events
		
			See: ROOT/uml/model-event.png (or uml.pdf)
			
			It goes along with observer patterns. Its a well known architectural pattern that let us define messages that encapsulates objects (Move, Game State...) to comfortably update any observers object of a particular state. It manages here the "conversation" between player and game instance, client-server communication.
			There is Player Events (that are indeed Moves), and Game Events that are messages the emanates from the game.
			The Event Dispatcher let us send message in an asynchronous way which is particularly suitable when a response is expected and we don't want to block the current thread at client side or if we want to update a whole lot of clients rapidly. 
		
		2.4 - Servers (Patterns: MVC, Facade, Factory, Dependency injection)
		
			See: ROOT/uml/server.png (or uml.pdf)
		
			The Controller class update the model (Game, Player...) and also update the client (views).
			Its a single entry point that simplify access to the application or in other word a Facade pattern.
			The wide majority of the classes in server side (but also client side) are instantiated with help of a set of Factories that have also Singleton behaviour (but not structure) guaranteed by the dependency injection container framework Spring (used in a naive manner as the same file take care of injection of server and client side classes but its just for sake of demonstration of the advantages of such a technique).
			
		2.5 - Graphical components (Pattern: Decorator, Flyweight, Strategy, Dynamic Proxy, Aspect style game state logging)
		
			See: ROOT/uml/components-graphical.png (or uml.pdf)
			See: ROOT/uml/component-board.png (or uml.pdf)
			
			2.5.1 - Graphical components
				The purpose is here to demonstrate that a display in a console may be configurable without much effort from the user except creation of some classes that specifies the correct display of the component considered but not anything such as modifying the whole algorithm.
				The pattern used has be Decorator (or Wrapper) for the reason that it may provide general behaviour that is after that configurable but does not destroy the basic interface that a graphical component shows to the external world.
				An example of used has been given with the board. The board is a graphical component that is constituted of different graphical component (border, separators, disks...) that are themselves sometime a graphical component composition. When the board operate the drawing algorithm it uses just one instance of each of the component in a basic Flyweight pattern style (a map is not used as it is considered to much heavy for a few classes but principle is conserved).
			
			2.5.2 - Aspect logging
				Anecdotally, the graphical component is also a Strategy has a different output stream algorithm may be given to the board class that allowed us to chose the console or a file to write the state of the game without much effort. On server side this property has been used to log the state of the game thanks to aspect technique (see: ROOT/in4rows/src/in4rows/aspect/GameTracing.aj).
				One file for each game played (uniquely identified by UUID) is logged under the ROOT/log directory with server prefix.
				
			2.5.3 - Dynamic proxy
				On client side a Dynamic Proxy is used to transparently log the game state on client side.
				see: ROOT/in4rows/src/in4rows/client/graphical/board/LoggableBoardProxy.java
				One file for each game played (uniquely identified by UUID) is logged under the ROOT/log directory with no prefix.

		