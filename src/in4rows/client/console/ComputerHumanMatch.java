package in4rows.client.console;

import in4rows.IController;
import in4rows.client.console.actions.ActionListener;
import in4rows.client.console.factory.ClientFactory;
import in4rows.client.graphical.Board;
import in4rows.client.view.composite.IBoardView;
import in4rows.event.GameEvent;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;
import in4rows.player.Player;
import in4rows.player.strategy.GameStrategy;

import java.util.ArrayList;

public class ComputerHumanMatch implements GameObserver, IMatch, Runnable {
	private ClientFactory f;
	private IController controller;

	private IBoardView boardView;
	private Board board;

	private Player localPlayer;
	private boolean isFinished = false;
	private GameReadable lastPosition;
	private String inputPlayer;

	public ComputerHumanMatch(ClientFactory f, IController controller,
			Player localPlayer) {
		super();
		this.controller = controller;
		this.localPlayer = localPlayer;
		this.f = f;
	}

	@Override
	public void init() throws GameNotProperlyInitializedException {
		this.board = new Board();
		this.boardView = f.createBoardView(new InputMoveActionListener());
		ArrayList<GameObserver> obs = new ArrayList<>();
		obs.add(this);
		controller.openGame(localPlayer, GameStrategy.Type.BASIC, obs);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		if (!localPlayer.getId().equals(e.getPlayerToPlay().getId()))
			return;
		
		if (GameEvent.Type.WIN.equals(e.getType())
				|| GameEvent.Type.DRAW.equals(e.getType()))
			updateEnd(e);
		
		lastPosition = gr;
		board.setGrid(lastPosition.getState());
		boardView.setBoard(board);
		display();
	}

	private void updateEnd(GameEvent e) {
		System.out.println(e.getMsg());
		System.out.println("End of the game.");
		isFinished = true;
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void display() {
		boardView.display();
	}

	public void displayError() {
		boardView.display();
	}

	private void play() {
		while (!isFinished)
			;
	}

	@Override
	public void run() {
		play();
	}

	@Override
	public void setFactory(ClientFactory f) {
		this.f = f;
	}

	public void setInputPlayer(String inputPlayer) {
		this.inputPlayer = inputPlayer;
		if (isLegalMove()) {
			int col = Integer.parseInt(this.inputPlayer);
			controller.playMove(f.createPlayerMoveEvent(lastPosition.getId(),
					localPlayer.getId(), f.createMove(col)));
		} else {
			displayError();
		}
	}

	private boolean isLegalMove() {
		int col = -1;
		try {
			col = Integer.parseInt(inputPlayer);
		} catch (Exception e) {
			return false;
		}
		if (col < 0 || col > lastPosition.getWidth())
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
