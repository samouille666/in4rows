package in4rows.client.console.factory;

import in4rows.IPlayerEventFactory;
import in4rows.client.console.actions.ActionListener;
import in4rows.client.console.actions.InputTextAction;
import in4rows.client.console.views.BasicViewContainer;
import in4rows.client.console.views.CompositeBoardView;
import in4rows.client.console.views.IPlayerRegistrationView;
import in4rows.client.console.views.LineFeedView;
import in4rows.client.console.views.SimpleBoardView;
import in4rows.client.console.views.SimpleUserRegistrationView;
import in4rows.client.console.views.TextView;
import in4rows.client.console.views.UserInputView;
import in4rows.client.graphical.board.Board;
import in4rows.client.graphical.board.IUpdatableBoard;
import in4rows.client.graphical.board.LoggableBoardProxy;
import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.event.PlayerEvent;
import in4rows.model.BasicMove;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.Player;

public class ClientFactory implements IPlayerEventFactory {

	IPlayerEventFactory playerEventFactory;

	private String screenSeparator = "*****************************************************************************************";

	public void setPlayerEventFactory(IPlayerEventFactory playerEventFactory) {
		this.playerEventFactory = playerEventFactory;
	}

	public BasicViewContainer createStartingScreen(
			ActionListener<String> actionListener) {

		BasicViewContainer startingScreen = new BasicViewContainer();
		startingScreen.addView(new LineFeedView(""));
		startingScreen.addView(new TextView(screenSeparator));
		startingScreen.addView(new TextView("Welcome to the in4rows game !"));
		startingScreen.addView(new LineFeedView(""));
		startingScreen.addView(new TextView("1. Human vs. Computer ?"));
		startingScreen.addView(new TextView("2. Human vs. Human ?"));
		startingScreen.addView(new TextView("3. Option ?"));
		startingScreen.addView(new TextView("4. Exit"));		
		startingScreen.addView(new LineFeedView(""));
		startingScreen.addView(new TextView(
				"Input the number of corresponding to your choice:"));
		UserInputView input = new UserInputView();
		InputTextAction action = new InputTextAction(actionListener);
		input.setAction(action);
		startingScreen.addView(input);

		return startingScreen;
	}

	public BasicViewContainer createStartingScreenError(
			ActionListener<String> actionListener) {

		BasicViewContainer startingScreenError = new BasicViewContainer();
		startingScreenError.addView(new LineFeedView(""));
		startingScreenError.addView(new TextView(screenSeparator));
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
		startingScreenError.addView(new TextView("4. Exit"));		
		startingScreenError.addView(new LineFeedView(""));
		startingScreenError.addView(new TextView(
				"Input the number of corresponding to your choice:"));
		UserInputView input = new UserInputView();
		InputTextAction action = new InputTextAction(actionListener);
		input.setAction(action);
		startingScreenError.addView(input);

		return startingScreenError;
	}

	public IPlayerRegistrationView createRegisterNewPlayerScreen(
			ActionListener<String> actionListener) {
		SimpleUserRegistrationView v = new SimpleUserRegistrationView();
		
		v.addView(new LineFeedView(""));
		v.addView(new TextView(screenSeparator));
		v.setTitleOfScreen(new TextView("Registration of a new player."));
		v.setSubTitleOfScreen(new TextView("Type your player name: "));
		
		UserInputView input = new UserInputView();
		InputTextAction action = new InputTextAction(actionListener);
		input.setAction(action);
		v.setUserNameInputView(input);		
		
		return v;
	}

	public BasicViewContainer createInputPlayerScreenError(
			ActionListener<String> actionListener) {

		BasicViewContainer inputPlayerIdScreenError = new BasicViewContainer();
		inputPlayerIdScreenError.addView(new LineFeedView(""));
		inputPlayerIdScreenError.addView(new TextView(screenSeparator));
		inputPlayerIdScreenError.addView(new LineFeedView(""));
		inputPlayerIdScreenError.addView(new TextView(
				"PLAYER ALREADY EXIST PLEASE REENTER..."));
		inputPlayerIdScreenError.addView(new LineFeedView(""));
		inputPlayerIdScreenError.addView(new TextView(
				"Enter your player name : "));
		UserInputView input = new UserInputView();
		InputTextAction action = new InputTextAction(actionListener);
		input.setAction(action);
		inputPlayerIdScreenError.addView(input);

		return inputPlayerIdScreenError;
	}

	public CompositeBoardView createBoardView(
			ActionListener<String> actionListener) {
		CompositeBoardView boardScreen = new CompositeBoardView();
		boardScreen.addView(new LineFeedView(""));
		boardScreen.addView(new LineFeedView(""));
		boardScreen.addView(new TextView(screenSeparator));

		TextView v = new TextView("You are now playing :");
		boardScreen.setUpperMsgView(v);
		boardScreen.addView(v);

		v = new TextView("");
		boardScreen.setInfoMsgView(v);
		boardScreen.addView(v);

		boardScreen.setBoardView(new SimpleBoardView());
		boardScreen.addView(new LineFeedView(""));

		v = new TextView("Input your move (column index from 0) : ");
		boardScreen.setInputMoveMsgView(v);
		boardScreen.addView(v);

		UserInputView input = new UserInputView();
		InputTextAction action = new InputTextAction(actionListener);
		input.setAction(action);
		boardScreen.setInputView(input);
		boardScreen.addView(input);

		return boardScreen;
	}

	public Move createMove(int col) {
		return new BasicMove(col);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type,
			GameReadable game, Player p, Move m) {
		return playerEventFactory.createPlayerEvent(type, game, p, m);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type,
			GameReadable game, Player p, Move m, String msg) {
		return playerEventFactory.createPlayerEvent(type, game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type, String gameId,
			String playerId, Move m) {
		return playerEventFactory.createPlayerEvent(type, gameId, playerId, m);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type, String gameId,
			String playerId, Move m, String msg) {
		return playerEventFactory.createPlayerEvent(type, gameId, playerId, m,
				msg);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p, Move m) {
		return playerEventFactory.createPlayerMoveEvent(game, p, m);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p,
			Move m, String msg) {
		return playerEventFactory.createPlayerMoveEvent(game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m) {
		return playerEventFactory.createPlayerMoveEvent(gameId, playerId, m);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m, String msg) {
		return playerEventFactory.createPlayerMoveEvent(gameId, playerId, m,
				msg);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p, Move m) {
		return playerEventFactory.createPlayerEndEvent(game, p, m);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p,
			Move m, String msg) {
		return playerEventFactory.createPlayerEndEvent(game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m) {
		return playerEventFactory.createPlayerEndEvent(gameId, playerId, m);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m, String msg) {
		return playerEventFactory
				.createPlayerEndEvent(gameId, playerId, m, msg);
	}

	public IUpdatableBoard createBoard() {
		IUpdatableBoard board = (IUpdatableBoard) LoggableBoardProxy
				.newInstance(new Board());
		((IGraphicalComponent) board).setOutStream(System.out);
		return board;
	}
}
