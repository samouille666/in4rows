package in4rows;

import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.model.GameWritable;
import in4rows.player.Player;
import in4rows.player.PlayerType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerController implements IController {

	private In4RowsFactory factory;

	private Map<String, GameWritable> games = new HashMap<>();
	private Map<String, Player> humanplayers = new HashMap<>();
	private Map<String, Player> computerPlayers = new HashMap<>();

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

}
