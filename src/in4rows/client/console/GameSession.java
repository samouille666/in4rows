package in4rows.client.console;

import in4rows.IController;
import in4rows.client.IGameSession;
import in4rows.client.console.factory.ClientFactory;
import in4rows.client.view.composite.IView;
import in4rows.event.GameEvent;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;
import in4rows.player.Player;
import in4rows.player.PlayerType;
import in4rows.player.strategy.GameStrategy.Type;

import java.util.ArrayList;
import java.util.List;

public class GameSession implements IGameSession, GameObserver {
	private ClientFactory factory;
	private IController controller;

	private IView screen1 = null;
	private IView screen1Error = null;
	private IView registerPLayer = null;
	private IView registerPLayerError = null;
	private IView gameView = null;

	private String screen1UserInput;
	private String playerName;

	private Player playingPlayer;
	private GameReadable openGame;
	private boolean gameIsFinished;
	private Integer move;

	public GameSession(ClientFactory f, IController controller) {
		setFactory(f);
		setController(controller);
		
		screen1 = factory.createStartingScreen(this);
		screen1Error = factory.createStartingScreenError(this);
		registerPLayer = factory.createInputPlayerScreen(this);
		registerPLayer = factory.createInputPlayerScreenError(this);
		gameView = factory.createBoardView(this);
	}

	public void setFactory(ClientFactory factory) {
		this.factory = factory;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	@Override
	public void setInputPlayer(String playerId) {
		this.playerName = playerId;
	}

	@Override
	public void setMove(Integer move) {
		this.move = move;
	}

	@Override
	public void setScreen1UserInput(String screen1choice) {
		this.screen1UserInput = screen1choice;
	}

	public String getInputPlayer() {
		return playerName;
	}

	public String getScreen1UserInput() {
		return screen1UserInput;
	}

	@Override
	public void startApp() {
		screen1.display();
		int choice = userChoice1();
		while (choice < 1 || choice > 3) {
			screen1Error.display();
			choice = userChoice1();
		}

		switch (choice) {
		case 1:
			registerPlayer();
			break;
		case 2:

			break;
		case 3:

			break;
		default:
			break;
		}
	}

	private int userChoice1() {
		Integer res = null;
		try {
			res = Integer.parseInt(getScreen1UserInput());
		} catch (NumberFormatException e) {
			return 0;
		}
		return res;
	}

	private boolean isPlayerRegister() {
		Player registerPLayer = null;
		try {
			registerPLayer = controller.createPlayer(PlayerType.HUMAN,
					getInputPlayer());
		} catch (ExistingPlayerException e) {
			return false;
		}
		playingPlayer = registerPLayer;
		return true;
	}

	private void registerPlayer() {
		registerPLayer.display();
		while (!isPlayerRegister()) {
			registerPLayerError.display();
		}
	}

	private void play() {
		List<GameObserver> lo = new ArrayList<>();
		lo.add(this);
		try {
			controller.openGame(playingPlayer, Type.BASIC, lo);
		} catch (GameNotProperlyInitializedException e) {
			e.printStackTrace();
			gameIsFinished = true;
		}

		while (!gameIsFinished)
			;
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		// TODO Auto-generated method stub

	}

}
