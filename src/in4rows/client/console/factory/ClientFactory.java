package in4rows.client.console.factory;

import in4rows.IController;
import in4rows.client.console.actions.Screen1Action;
import in4rows.client.console.views.ChoicesView;
import in4rows.client.console.views.ClientConsoleScreen;
import in4rows.client.console.views.ConsoleInfoView;
import in4rows.client.console.views.ConsoleInputNumberView;
import in4rows.client.console.views.LineFeedView;

public class ClientFactory {
	
	private ClientConsoleScreen startingScreen;
	private ClientConsoleScreen startingScreenError; 
	
	public ClientConsoleScreen createStartingScreen(IController controller) {
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
	
	public ClientConsoleScreen createStartingScreenError(IController controller) {
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
	
}
