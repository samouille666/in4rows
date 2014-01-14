package in4rows.client;

import in4rows.IController;
import in4rows.event.EventDispatcher;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.player.Player;
import in4rows.player.PlayerType;
import in4rows.player.strategy.GameStrategy.Type;

import java.util.List;

public class In4RowsClientProxyController implements IController {
	private EventDispatcher dispatcher;
	private IController distantController;

	public In4RowsClientProxyController(IController c) {
		distantController = c;
	}

	@Override
	public void openGame(Player p1, Type machineStrategy, List<GameObserver> l)
			throws GameNotProperlyInitializedException {
		dispatcher.executeEventAndTerminate(new OpenGameWorker(p1,
				machineStrategy, l));
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

	@Override
	public void playMove(PlayerEvent e) throws ErroneousPlayerEventException {
		dispatcher.executeEventAndTerminate(new PLayMoveWorker(e));
	}

	public void setDistantController(IController distantController) {
		this.distantController = distantController;
	}

	public void setDispatcher(EventDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	private class OpenGameWorker implements Runnable {
		private Player p1;
		private Type machineStrategy;
		private List<GameObserver> l;

		public OpenGameWorker(Player p1, Type machineStrategy,
				List<GameObserver> l) {
			super();
			this.p1 = p1;
			this.machineStrategy = machineStrategy;
			this.l = l;
		}

		@Override
		public void run() {
			try {
				distantController.openGame(p1, machineStrategy, l);
			} catch (GameNotProperlyInitializedException e) {
				e.printStackTrace();
			}
		}
	}

	private class PLayMoveWorker implements Runnable {
		private PlayerEvent e;

		public PLayMoveWorker(PlayerEvent e) {
			super();
			this.e = e;
		}

		@Override
		public void run() {

			try {
				distantController.playMove(e);
			} catch (ErroneousPlayerEventException e) {
				e.printStackTrace();
			}

		}
	}
}
