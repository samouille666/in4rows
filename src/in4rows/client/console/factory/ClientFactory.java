package in4rows.client.console.factory;

import in4rows.client.IClientController;
import in4rows.client.console.actions.InputPlayerAction;
import in4rows.client.console.actions.Screen1Action;
import in4rows.client.console.views.ChoicesView;
import in4rows.client.console.views.ClientConsoleScreen;
import in4rows.client.console.views.ConsoleInfoView;
import in4rows.client.console.views.ConsoleInputNumberView;
import in4rows.client.console.views.LineFeedView;

public class ClientFactory {
	
	private ClientConsoleScreen startingScreen;
	private ClientConsoleScreen startingScreenError; 
	private ClientConsoleScreen inputPlayerIdScreen; 
	private ClientConsoleScreen inputPlayerIdScreenError; 
	
	public ClientConsoleScreen createStartingScreen(IClientController controller) {
		if(startingScreen == null){
			startingScreen = new ClientConsoleScreen();
			startingScreen.addView(new ConsoleInfoView("****************************************************************"));
			startingScreen.addView(new ChoicesView("Welcome to the in4rows game !"));
			startingScreen.addView(new LineFeedView(""));
			startingScreen.addView(new ChoicesView("1. Human vs. Computer ?"));
			startingScreen.addView(new ChoicesView("2. Human vs. Human ?"));
			startingScreen.addView(new ChoicesView("3. Option ?"));
			startingScreen.addView(new LineFeedView(""));			
			startingScreen.addView(new ConsoleInfoView("Input the number of corresponding to your choice:"));
			ConsoleInputNumberView input = new ConsoleInputNumberView();
			Screen1Action action = new Screen1Action(controller);
			input.setAction(action);
			startingScreen.addView(input);
		}
		return startingScreen;
	}
	
	public ClientConsoleScreen createStartingScreenError(IClientController controller) {
		if(startingScreenError == null){
			startingScreenError = new ClientConsoleScreen();
			startingScreenError.addView(new ConsoleInfoView("****************************************************************"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new ConsoleInfoView("THE INPUT IS ERRONEOUS PLEASE REENTER..."));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new ChoicesView("Welcome to the in4rows game !"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new ChoicesView("1. Human vs. Computer ?"));
			startingScreenError.addView(new ChoicesView("2. Human vs. Human ?"));
			startingScreenError.addView(new ChoicesView("3. Option ?"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new ConsoleInfoView("Input the number of corresponding to your choice:"));
			ConsoleInputNumberView input = new ConsoleInputNumberView();
			Screen1Action action = new Screen1Action(controller);
			input.setAction(action);
			startingScreenError.addView(input);			
		}
		return startingScreenError;
	}
		
	public ClientConsoleScreen createInputPlayerScreen(IClientController controller) {
		if(inputPlayerIdScreen == null){
			inputPlayerIdScreen = new ClientConsoleScreen();
			inputPlayerIdScreen.addView(new ConsoleInfoView("****************************************************************"));
			inputPlayerIdScreen.addView(new ChoicesView("Enter your player name : "));
			inputPlayerIdScreen.addView(new LineFeedView(""));
			ConsoleInputNumberView input = new ConsoleInputNumberView();
			InputPlayerAction action = new InputPlayerAction(controller);
			input.setAction(action);
			inputPlayerIdScreen.addView(input);			
		}
		return inputPlayerIdScreen;
	}
	
	
	public ClientConsoleScreen createInputPlayerScreenError(IClientController controller) {
		if(inputPlayerIdScreenError == null){
			inputPlayerIdScreenError = new ClientConsoleScreen();
			inputPlayerIdScreenError.addView(new ConsoleInfoView("****************************************************************"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new ConsoleInfoView("PLAYER ALREADY EXIST PLEASE REENTER..."));
			startingScreenError.addView(new LineFeedView(""));			
			inputPlayerIdScreenError.addView(new ChoicesView("Enter your player name : "));
			inputPlayerIdScreenError.addView(new LineFeedView(""));
			ConsoleInputNumberView input = new ConsoleInputNumberView();
			InputPlayerAction action = new InputPlayerAction(controller);
			input.setAction(action);
			inputPlayerIdScreenError.addView(input);			
		}
		return inputPlayerIdScreenError;
	}	
	
}
