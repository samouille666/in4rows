package in4rows;

import in4rows.exception.ExistingPlayerException;
import in4rows.game.ObservableGame;
import in4rows.model.GameWritable;
import in4rows.player.Player;
import in4rows.player.PlayerType;

import java.util.HashMap;
import java.util.Map;

public class ServerController implements IController {

	private In4RowsFactory factory;

	private Map<String, GameWritable> games = new HashMap<>();
	private Map<String, Player> players = new HashMap<>();

	@Override
	public ObservableGame openGame(PlayerType p1, PlayerType p2) {

		return null;
	}

	@Override
	public Player createPlayer(PlayerType t, String id)
			throws ExistingPlayerException {
		if (players.get(id) != null)
			throw new ExistingPlayerException("PLayer already exist !");
		players.put(id, factory.createPlayer(t, id));
		return players.get(id);
	}

	public void setFactory(In4RowsFactory factory) {
		this.factory = factory;
	}

}
