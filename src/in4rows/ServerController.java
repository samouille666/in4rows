package in4rows;

import in4rows.event.PlayerEvent;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.player.Player;
import in4rows.player.PlayerType;
import in4rows.player.strategy.GameStrategy;
import in4rows.player.strategy.GameStrategy.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerController implements IController {

	private In4RowsFactory factory;

	private Map<String, Player> humanplayers = new HashMap<>();
	private Map<String, ObservableGame> games = new HashMap<>();
	private Map<GameStrategy.Type, Player> computerPlayers = new HashMap<>();

	@Override
	public void openGame(Player p1, Type strategyType, List<GameObserver> l)
			throws GameNotProperlyInitializedException {

		if (p1 == null || strategyType == null || l == null || l.isEmpty())
			throw new GameNotProperlyInitializedException(
					"Parameter(s) to open game not consistent.");

		if (!computerPlayers.containsKey(strategyType))
			computerPlayers.put(strategyType,
					factory.createMachinePlayer(strategyType));

		Player machinePlayer = computerPlayers.get(strategyType);
		ObservableGame g = factory.createGame(p1, machinePlayer);

		for (GameObserver o : l)
			g.attachObs(o);
		games.put(g.getId(), g);
		
		g.start();
	}

	@Override
	public ObservableGame openGame(Player p1, Player p2, List<GameObserver> l)
			throws GameNotProperlyInitializedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player createPlayer(PlayerType t, String id)
			throws ExistingPlayerException {
		if (humanplayers.get(id) != null)
			throw new ExistingPlayerException("PLayer already exist !");
		humanplayers.put(id, factory.createHumanPlayer(t, id));
		return humanplayers.get(id);
	}

	public void setFactory(In4RowsFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void playMove(PlayerEvent e) {
		// TODO Auto-generated method stub
		
	}

}
