package in4rows.client.console;

import in4rows.IController;
import in4rows.client.IClientSession;
import in4rows.client.console.actions.AbstractActionListener;
import in4rows.client.console.actions.Action;
import in4rows.client.console.actions.ActionListener;
import in4rows.client.console.factory.ClientFactory;
import in4rows.client.view.composite.IView;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.player.Player;
import in4rows.player.PlayerType;

public class ClientSession implements IClientSession {
	private ClientFactory factory;
	private IController controller;

	private IView screen1 = null;
	private IView screen1Error = null;
	private IView registerPLayer = null;
	private IView registerPLayerError = null;

	private String screen1UserInput;
	private String playerName;

	private Player currentPlayer;

	public ClientSession(ClientFactory f, IController controller) {
		setFactory(f);
		setController(controller);

		Screen1ActionListener l1 = new Screen1ActionListener(this);
		screen1 = factory.createStartingScreen(l1);
		screen1Error = factory.createStartingScreenError(l1);

		RegisterUserNameListener l2 = new RegisterUserNameListener(this);
		registerPLayer = factory.createInputPlayerScreen(l2);
		registerPLayerError = factory.createInputPlayerScreenError(l2);

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
			play();
			break;
		case 2:

			break;
		case 3:

			break;
		default:
			break;
		}
	}

	private void play() {
		IMatch m = new ComputerHumanMatch(factory, controller, currentPlayer);
		try {
			m.init();
			m.play();
		} catch (GameNotProperlyInitializedException e) {
			e.printStackTrace();
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
		currentPlayer = registerPLayer;
		return true;
	}

	private void registerPlayer() {
		registerPLayer.display();
		while (!isPlayerRegister()) {
			registerPLayerError.display();
		}
	}

	public class InputTextAction extends Action<String> {
		public InputTextAction(ActionListener<String> actionListener) {
			super(actionListener);
		}

		public void performAction(String intput) {
			actionListener.setInput(intput);
		};
	}

	private class Screen1ActionListener extends AbstractActionListener<String> {

		public Screen1ActionListener(IClientSession session) {
			super(session);
		}

		@Override
		public void setInput(String input) {
			session.setScreen1UserInput(input);
		}
	}

	private class RegisterUserNameListener extends
			AbstractActionListener<String> {

		public RegisterUserNameListener(IClientSession session) {
			super(session);
		}

		@Override
		public void setInput(String input) {
			session.setInputPlayer(input);
		}
	}

}
