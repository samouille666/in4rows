package in4rows.client.console;

import in4rows.IController;
import in4rows.client.IClientSession;
import in4rows.client.console.actions.AbstractActionListener;
import in4rows.client.console.actions.Action;
import in4rows.client.console.actions.ActionListener;
import in4rows.client.console.factory.ClientFactory;
import in4rows.client.console.views.IPlayerRegistrationView;
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
	private IPlayerRegistrationView registerPlayer = null;
	private IView registerPlayerError = null;

	private String screen1UserInput;
	private String playerName;

	private Player humanVsMachinePlayer;
	private Player humanVsHumanPlayer1;
	private Player humanVsHumanPlayer2;

	public ClientSession(ClientFactory f, IController controller) {
		setFactory(f);
		setController(controller);

		Screen1ActionListener l1 = new Screen1ActionListener(this);
		screen1 = factory.createStartingScreen(l1);
		screen1Error = factory.createStartingScreenError(l1);

		RegisterUserNameListener l2 = new RegisterUserNameListener(this);
		registerPlayer = factory.createRegisterNewPlayerScreen(l2);
		registerPlayerError = factory.createInputPlayerScreenError(l2);

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

		boolean sessionNotFinished = true;

		while (sessionNotFinished) {
			screen1.display();
			int choice = userChoice1();
			while (choice < 1 || choice > 4) {
				screen1Error.display();
				choice = userChoice1();
			}

			switch (choice) {
			case 1:
				humanVsMachinePlayer = registerPlayer("Type your player name : ");
				playComputerVsHumanMatch();
				break;
			case 2:
				humanVsHumanPlayer1 = registerPlayer("Type first player name : ");
				humanVsHumanPlayer2 = registerPlayer("Type second player name : ");
				playHumanVsHumanMatch();
				break;
			case 3:
				System.err.println("functionality not implemented");
				sessionNotFinished = false;
				break;
			case 4:
				System.out.println("\n\nExit game....");
				sessionNotFinished = false;
				break;
			default:
				break;
			}
		}
	}

	private void playComputerVsHumanMatch() {
		IMatch m = new ComputerHumanMatch(factory, controller,
				humanVsMachinePlayer);
		try {
			m.init();
			m.play();
		} catch (GameNotProperlyInitializedException e) {
			e.printStackTrace();
			m.setFinished(true);
		}
	}

	private void playHumanVsHumanMatch() {
		IMatch m = new HumanHumanMatch(factory, controller,
				humanVsHumanPlayer1, humanVsHumanPlayer2);
		try {
			m.init();
			m.play();
		} catch (GameNotProperlyInitializedException e) {
			e.printStackTrace();
			m.setFinished(true);
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

	private Player isPlayerRegister() {
		Player registerPLayer = null;
		try {
			registerPLayer = controller.createPlayer(PlayerType.HUMAN,
					getInputPlayer());
		} catch (ExistingPlayerException e) {
			return null;
		}
		return registerPLayer;
	}

	private Player registerPlayer(String diplayInformation) {
		registerPlayer.getSubTitleOfScreen().setInstruction(diplayInformation);
		registerPlayer.display();

		Player registeredPlayer = isPlayerRegister();
		while (registeredPlayer == null) {
			registerPlayerError.display();
			registeredPlayer = isPlayerRegister();
		}
		return registeredPlayer;
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
