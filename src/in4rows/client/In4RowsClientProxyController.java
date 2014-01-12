package in4rows.client;

import java.util.List;

import in4rows.IController;
import in4rows.client.console.factory.ClientFactory;
import in4rows.client.view.composite.IView;
import in4rows.event.GameEvent;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.model.GameReadable;
import in4rows.player.Player;
import in4rows.player.PlayerType;

public class In4RowsClientProxyController implements IClientController,
		IController, GameObserver{
	private IController distantController;

	private ClientFactory factory;

	private IView screen1 = null;
	private IView screen1Error = null;
	private IView registerPLayer = null;
	private IView registerPLayerError = null;
	private IView gameView = null;

	private String screen1UserInput;
	private String playerName;

	private Player playingPlayer;
	private GameReadable openGame;

	public In4RowsClientProxyController(ClientFactory f) {
		factory = f;
		screen1 = factory.createStartingScreen(this);
		screen1Error = factory.createStartingScreenError(this);
		registerPLayer = factory.createInputPlayerScreen(this);
		registerPLayer = factory.createInputPlayerScreenError(this);
	}

	@Override
	public void setInputPlayer(String playerId) {
		this.playerName = playerId;
	}

	public String getInputPlayer() {
		return playerName;
	}

	@Override
	public void setScreen1UserInput(String screen1choice) {
		this.screen1UserInput = screen1choice;
	}

	public String getScreen1UserInput() {
		return screen1UserInput;
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
			registerPLayer = createPlayer(PlayerType.HUMAN, getInputPlayer());
		} catch (ExistingPlayerException e) {
			return false;
		}
		playingPlayer = registerPLayer;
		return true;
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

	private void registerPlayer() {
		registerPLayer.display();
		while (!isPlayerRegister()) {
			registerPLayerError.display();
		}
	}

	private void play(){
		//openGame = openGame(playingPlayer, null);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		
		
	}
	
	public void setFactory(ClientFactory factory) {
		this.factory = factory;
	}

	@Override
	public ObservableGame openGame(Player p1, List<GameObserver> l)
			throws GameNotProperlyInitializedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ObservableGame openGame(Player p1, Player p2, List<GameObserver> l)
			throws GameNotProperlyInitializedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player createPlayer(PlayerType p, String id)
			throws ExistingPlayerException {
		return distantController.createPlayer(p, id);
	}

	public void setDistantController(IController distantController) {
		this.distantController = distantController;
	}

}
