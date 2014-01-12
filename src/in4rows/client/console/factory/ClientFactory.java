package in4rows.client.console.factory;

import in4rows.client.IClientController;
import in4rows.client.console.actions.InputPlayerAction;
import in4rows.client.console.actions.Screen1Action;
import in4rows.client.console.views.BasicViewContainer;
import in4rows.client.console.views.LineFeedView;
import in4rows.client.console.views.TextView;
import in4rows.client.console.views.UserInputView;

public class ClientFactory {
	
	private BasicViewContainer startingScreen;
	private BasicViewContainer startingScreenError; 
	private BasicViewContainer inputPlayerIdScreen; 
	private BasicViewContainer inputPlayerIdScreenError; 
	
	public BasicViewContainer createStartingScreen(IClientController controller) {
		if(startingScreen == null){
			startingScreen = new BasicViewContainer();
			startingScreen.addView(new TextView("****************************************************************"));
			startingScreen.addView(new TextView("Welcome to the in4rows game !"));
			startingScreen.addView(new LineFeedView(""));
			startingScreen.addView(new TextView("1. Human vs. Computer ?"));
			startingScreen.addView(new TextView("2. Human vs. Human ?"));
			startingScreen.addView(new TextView("3. Option ?"));
			startingScreen.addView(new LineFeedView(""));			
			startingScreen.addView(new TextView("Input the number of corresponding to your choice:"));
			UserInputView input = new UserInputView();
			Screen1Action action = new Screen1Action(controller);
			input.setAction(action);
			startingScreen.addView(input);
		}
		return startingScreen;
	}
	
	public BasicViewContainer createStartingScreenError(IClientController controller) {
		if(startingScreenError == null){
			startingScreenError = new BasicViewContainer();
			startingScreenError.addView(new TextView("****************************************************************"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new TextView("THE INPUT IS ERRONEOUS PLEASE REENTER..."));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new TextView("Welcome to the in4rows game !"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new TextView("1. Human vs. Computer ?"));
			startingScreenError.addView(new TextView("2. Human vs. Human ?"));
			startingScreenError.addView(new TextView("3. Option ?"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new TextView("Input the number of corresponding to your choice:"));
			UserInputView input = new UserInputView();
			Screen1Action action = new Screen1Action(controller);
			input.setAction(action);
			startingScreenError.addView(input);			
		}
		return startingScreenError;
	}
		
	public BasicViewContainer createInputPlayerScreen(IClientController controller) {
		if(inputPlayerIdScreen == null){
			inputPlayerIdScreen = new BasicViewContainer();
			inputPlayerIdScreen.addView(new TextView("****************************************************************"));
			inputPlayerIdScreen.addView(new TextView("Enter your player name : "));
			inputPlayerIdScreen.addView(new LineFeedView(""));
			UserInputView input = new UserInputView();
			InputPlayerAction action = new InputPlayerAction(controller);
			input.setAction(action);
			inputPlayerIdScreen.addView(input);			
		}
		return inputPlayerIdScreen;
	}
	
	
	public BasicViewContainer createInputPlayerScreenError(IClientController controller) {
		if(inputPlayerIdScreenError == null){
			inputPlayerIdScreenError = new BasicViewContainer();
			inputPlayerIdScreenError.addView(new TextView("****************************************************************"));
			startingScreenError.addView(new LineFeedView(""));
			startingScreenError.addView(new TextView("PLAYER ALREADY EXIST PLEASE REENTER..."));
			startingScreenError.addView(new LineFeedView(""));			
			inputPlayerIdScreenError.addView(new TextView("Enter your player name : "));
			inputPlayerIdScreenError.addView(new LineFeedView(""));
			UserInputView input = new UserInputView();
			InputPlayerAction action = new InputPlayerAction(controller);
			input.setAction(action);
			inputPlayerIdScreenError.addView(input);			
		}
		return inputPlayerIdScreenError;
	}	
	
}
