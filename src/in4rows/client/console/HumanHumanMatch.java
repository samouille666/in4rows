package in4rows.client.console;

import in4rows.IController;
import in4rows.client.console.actions.ActionListener;
import in4rows.client.console.factory.ClientFactory;
import in4rows.client.console.views.IBoardScreenView;
import in4rows.client.graphical.IUpdatableBoard;
import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.event.GameEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;
import in4rows.player.Player;

import java.util.ArrayList;

public class HumanHumanMatch implements GameObserver, IMatch {
	private ClientFactory f;
	private IController controller;

	private IBoardScreenView boardView;
	private IUpdatableBoard board;

	private Player p1;
	private Player p2;
	
	private Player toPlay;
	
	private boolean isFinished = false;
	private GameReadable lastPosition;
	private String inputPlayer;

	public HumanHumanMatch(ClientFactory f, IController controller,
			Player p1, Player p2) {
		super();
		this.controller = controller;
		this.p1 = p1;
		this.p2 = p2;
		this.f = f;
	}

	@Override
	public void init() throws GameNotProperlyInitializedException {
		this.board = f.createBoard();
		this.boardView = f.createBoardView(new InputMoveActionListener());
		ArrayList<GameObserver> obs = new ArrayList<>();
		obs.add(this);
		controller.openGame(p1, p2, obs);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {		
		toPlay = computePlayerToPlay(e);
		
		if (GameEvent.Type.WIN.equals(e.getType())
				|| GameEvent.Type.DRAW.equals(e.getType())) {
			isFinished = true;
			boardView.setInputPossible(false);
			boardView.getInputMoveMsgView().setInstruction("");
		}

		lastPosition = gr;
		board.setGame(lastPosition);
		boardView.getInfoMsgView().setInstruction(e.getMsg());
		boardView.setBoard((IGraphicalComponent) board);
		display();
	}
	
	private Player computePlayerToPlay(GameEvent e) {
		if (p1.getId().equals(e.getPlayerToPlay().getId()) ) 
			return p1;		
		return p2;
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void display() {
		boardView.display();
	}

	public void displayError(String errorMsg) {
		boardView.getInfoMsgView().setInstruction(errorMsg);
		boardView.display();
	}

	public void play() {
		while (!isFinished)
			;
	}

	@Override
	public void setFactory(ClientFactory f) {
		this.f = f;
	}

	public void setInputPlayer(String inputPlayer) {
		this.inputPlayer = inputPlayer;
		if (isLegalMove()) {
			int col = Integer.parseInt(this.inputPlayer);
			try {
				controller.playMove(f.createPlayerMoveEvent(
						lastPosition.getId(), toPlay.getId(),
						f.createMove(col)));
			} catch (ErroneousPlayerEventException e) {
				displayError("||>>>>>>>>>> Move is not legal. <<<<<<<<<<<<<\n||"
						+ e.getMessage());
			}
		} else {
			displayError("||>>>>>>>>>> Move is not legal. <<<<<<<<<<<<<\n||Please reenter it...");
		}
	}

	private boolean isLegalMove() {
		int col = -1;
		try {
			col = Integer.parseInt(inputPlayer);
		} catch (Exception e) {
			return false;
		}
		if (col < 0 || col >= lastPosition.getWidth())
			return false;
		return true;
	}

	private class InputMoveActionListener implements ActionListener<String> {

		@Override
		public void setInput(String input) {
			setInputPlayer(input);
		}
	}
}
