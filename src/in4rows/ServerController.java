package in4rows;

import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.model.GameReadable;
import in4rows.player.GameObserverComputerPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerType;
import in4rows.player.strategy.GameStrategy;
import in4rows.player.strategy.GameStrategy.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerController implements IController, GameStopper {

	private In4RowsFactory factory;

	private Map<String, Player> humanplayers = new HashMap<>();
	private Map<String, ObservableGame> games = new HashMap<>();
	private Map<GameStrategy.Type, GameObserverComputerPlayer> computerPlayers = new HashMap<>();

	@Override
	public void openGame(Player p1, Type strategyType, List<GameObserver> l)
			throws GameNotProperlyInitializedException {

		if (p1 == null || strategyType == null || l == null || l.isEmpty())
			throw new GameNotProperlyInitializedException(
					"Parameter(s) to open game not consistent.");

		if (!computerPlayers.containsKey(strategyType))
			computerPlayers.put(strategyType, factory
					.createObserverMachinePlayer(strategyType, this, this));

		GameObserverComputerPlayer machinePlayer = computerPlayers
				.get(strategyType);
		ObservableGame g = factory.createGame(p1, machinePlayer);

		l.add(machinePlayer);
		for (GameObserver o : l)
			g.attachObs(o);
		games.put(g.getId(), g);

		g.start();
	}

	@Override
	public void openGame(Player p1, Player p2, List<GameObserver> l)
			throws GameNotProperlyInitializedException {
		if (p1 == null || p2 == null || l == null || l.isEmpty())
			throw new GameNotProperlyInitializedException(
					"Parameter(s) to open game not consistent.");
		ObservableGame g = factory.createGame(p1, p2);
		for (GameObserver o : l)
			g.attachObs(o);
		games.put(g.getId(), g);
		g.start();
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
	public void playMove(PlayerEvent e) throws ErroneousPlayerEventException {
		if (e == null || e.getGameId() == null)
			return;
		ObservableGame g = games.get(e.getGameId());
		GameEvent gEvt = g.play(e);
		if (GameEvent.Type.DRAW.equals(gEvt.getType())
				|| GameEvent.Type.WIN.equals(gEvt.getType()))
			stop(g);
	}

	@Override
	public void stop(GameReadable g) {
		ObservableGame og = games.get(g.getId());
		if (og == null)
			return;
		og.end();
		games.remove(og);
	}

}
