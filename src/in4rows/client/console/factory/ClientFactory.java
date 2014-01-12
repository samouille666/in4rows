package in4rows.client.console.factory;

import in4rows.client.IGameSession;
import in4rows.client.console.actions.InputPlayerAction;
import in4rows.client.console.actions.Screen1Action;
import in4rows.client.console.views.BasicViewContainer;
import in4rows.client.console.views.CompositeBoardView;
import in4rows.client.console.views.SimpleBoardView;
import in4rows.client.console.views.LineFeedView;
import in4rows.client.console.views.TextView;
import in4rows.client.console.views.UserInputView;

public class ClientFactory {

	public BasicViewContainer createStartingScreen(IGameSession session) {

		BasicViewContainer startingScreen = new BasicViewContainer();
		startingScreen.addView(new TextView(
				"*******************************************"));
		startingScreen.addView(new TextView("Welcome to the in4rows game !"));
		startingScreen.addView(new LineFeedView(""));
		startingScreen.addView(new TextView("1. Human vs. Computer ?"));
		startingScreen.addView(new TextView("2. Human vs. Human ?"));
		startingScreen.addView(new TextView("3. Option ?"));
		startingScreen.addView(new LineFeedView(""));
		startingScreen.addView(new TextView(
				"Input the number of corresponding to your choice:"));
		UserInputView input = new UserInputView();
		Screen1Action action = new Screen1Action(session);
		input.setAction(action);
		startingScreen.addView(input);

		return startingScreen;
	}

	public BasicViewContainer createStartingScreenError(
			IGameSession session) {

		BasicViewContainer startingScreenError = new BasicViewContainer();
		startingScreenError.addView(new TextView(
				"*******************************************"));
		startingScreenError.addView(new LineFeedView(""));
		startingScreenError.addView(new TextView(
				"THE INPUT IS ERRONEOUS PLEASE REENTER..."));
		startingScreenError.addView(new LineFeedView(""));
		startingScreenError.addView(new TextView(
				"Welcome to the in4rows game !"));
		startingScreenError.addView(new LineFeedView(""));
		startingScreenError.addView(new TextView("1. Human vs. Computer ?"));
		startingScreenError.addView(new TextView("2. Human vs. Human ?"));
		startingScreenError.addView(new TextView("3. Option ?"));
		startingScreenError.addView(new LineFeedView(""));
		startingScreenError.addView(new TextView(
				"Input the number of corresponding to your choice:"));
		UserInputView input = new UserInputView();
		Screen1Action action = new Screen1Action(session);
		input.setAction(action);
		startingScreenError.addView(input);

		return startingScreenError;
	}

	public BasicViewContainer createInputPlayerScreen(
			IGameSession session) {
		BasicViewContainer inputPlayerIdScreen = new BasicViewContainer();
		inputPlayerIdScreen.addView(new TextView(
				"*******************************************"));
		inputPlayerIdScreen.addView(new TextView("Enter your player name : "));
		UserInputView input = new UserInputView();
		InputPlayerAction action = new InputPlayerAction(session);
		input.setAction(action);
		inputPlayerIdScreen.addView(input);

		return inputPlayerIdScreen;
	}

	public BasicViewContainer createInputPlayerScreenError(
			IGameSession session) {

		BasicViewContainer inputPlayerIdScreenError = new BasicViewContainer();
		inputPlayerIdScreenError.addView(new TextView(
				"*******************************************"));
		inputPlayerIdScreenError.addView(new LineFeedView(""));
		inputPlayerIdScreenError.addView(new TextView(
				"PLAYER ALREADY EXIST PLEASE REENTER..."));
		inputPlayerIdScreenError.addView(new LineFeedView(""));
		inputPlayerIdScreenError.addView(new TextView(
				"Enter your player name : "));
		UserInputView input = new UserInputView();
		InputPlayerAction action = new InputPlayerAction(session);
		input.setAction(action);
		inputPlayerIdScreenError.addView(input);

		return inputPlayerIdScreenError;
	}

	public CompositeBoardView createBoardView(IGameSession session) {
		CompositeBoardView boardScreen = new CompositeBoardView();
		boardScreen.addView(new TextView(
				"*******************************************"));
		boardScreen.addView(new LineFeedView(""));
		boardScreen.setBoardView(new SimpleBoardView());
		
		return boardScreen;
	}
}
