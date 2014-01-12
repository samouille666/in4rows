package in4rows.client;

import in4rows.IController;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.player.Player;
import in4rows.player.PlayerType;
import in4rows.player.strategy.GameStrategy.Type;

import java.util.List;

public class In4RowsClientProxyController implements IController {
	private IController distantController;

	public In4RowsClientProxyController(IController c) {
		distantController = c;
	}

	@Override
	public void openGame(Player p1, Type machineStrategy, List<GameObserver> l)
			throws GameNotProperlyInitializedException {
		distantController.openGame(p1, machineStrategy, l);
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
