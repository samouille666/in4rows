package in4rows;

import in4rows.event.BasicGameEvent;
import in4rows.event.EventDispatcher;
import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent;
import in4rows.game.BasicGame;
import in4rows.game.BasicObservableGame;
import in4rows.game.ObservableGame;
import in4rows.model.BasicMove;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.BasicComputerPlayer;
import in4rows.player.BasicPlayer;
import in4rows.player.ComputerPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerTurn;
import in4rows.player.PlayerType;
import in4rows.player.strategy.AgressiveStrategy;
import in4rows.player.strategy.AverageStrategy;
import in4rows.player.strategy.BasicStrategy;
import in4rows.player.strategy.ExperimentedStrategy;
import in4rows.player.strategy.GameStrategy;
import in4rows.player.strategy.GameStrategy.Type;
import in4rows.player.strategy.NaiveStrategy;

public class In4RowsServerFactory implements In4RowsFactory,
		IPlayerEventFactory {

	private EventDispatcher dispatcher;
	private IPlayerEventFactory playerEventFactory;

	public void setDispatcher(EventDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void setPlayerEventFactory(IPlayerEventFactory playerEventFactory) {
		this.playerEventFactory = playerEventFactory;
	}

	@Override
	public Player createHumanPlayer(PlayerType type, String identifier) {
		return new BasicPlayer(identifier, type);
	}

	@Override
	public ObservableGame createGame(Player p1, Player p2, Disk color,
			PlayerTurn t, int width, int height) {
		BasicGame bg = new BasicGame(p1, color, t, width, height);
		bg.setPlayer2(p2);
		bg.setFactory(this);
		return new BasicObservableGame(bg, dispatcher);
	}

	@Override
	public ObservableGame createGame(Player p1, Player p2) {
		return createGame(p1, p2, Disk.BLACK);
	}

	@Override
	public ObservableGame createGame(Player p1, Player p2, Disk color) {
		return createGame(p1, p2, color, PlayerTurn.YES, 10, 10);
	}

	@Override
	public ObservableGame createGame(Player p1, Player p2, Disk color,
			PlayerTurn t) {
		return createGame(p1, p2, color, t, 10, 10);
	}

	@Override
	public ObservableGame createGame(Player p1, Player p2, PlayerTurn t) {
		return createGame(p1, p2, Disk.BLACK, t);
	}

	@Override
	public GameEvent createStartEvent(GameReadable g, Player toPlay,
			Player opponent) {
		String msg = "Game " + g.getId() + " started. " + "Player "
				+ toPlay.getId() + " to play.";
		return new BasicGameEvent(GameEvent.Type.START, g, null, msg, toPlay,
				opponent);
	}

	@Override
	public GameStrategy createStrategy(Type t) {
		switch (t) {
		case NAIVE:
			//return new NaiveStrategy();
		case EXPERIMENTED:
			//return new ExperimentedStrategy();
		case AGRESSIVE:
			//return new AgressiveStrategy();
		case AVERAGE:
			//return new AverageStrategy();
		case BASIC:
			//return new BasicStrategy();
		default:
			return new BasicStrategy();
		}
	}

	@Override
	public ComputerPlayer createMachinePlayer(Type t) {
		return new BasicComputerPlayer(String.valueOf(t), createStrategy(t));
	}

	@Override
	public Move createMove(int col) {
		return new BasicMove(col);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type,
			GameReadable game, Player p, Move m) {
		return playerEventFactory.createPlayerEvent(type, game, p, m);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type,
			GameReadable game, Player p, Move m, String msg) {
		return playerEventFactory.createPlayerEvent(type, game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type, String gameId,
			String playerId, Move m) {
		return playerEventFactory.createPlayerEvent(type, gameId, playerId, m);
	}

	@Override
	public PlayerEvent createPlayerEvent(PlayerEvent.Type type, String gameId,
			String playerId, Move m, String msg) {
		return playerEventFactory.createPlayerEvent(type, gameId, playerId, m,
				msg);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p, Move m) {
		return playerEventFactory.createPlayerMoveEvent(game, p, m);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p,
			Move m, String msg) {
		return playerEventFactory.createPlayerMoveEvent(game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m) {
		return playerEventFactory.createPlayerMoveEvent(gameId, playerId, m);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m, String msg) {
		return playerEventFactory.createPlayerMoveEvent(gameId, playerId, m,
				msg);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p, Move m) {
		return playerEventFactory.createPlayerEndEvent(game, p, m);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p,
			Move m, String msg) {
		return playerEventFactory.createPlayerEndEvent(game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m) {
		return playerEventFactory.createPlayerEndEvent(gameId, playerId, m);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m, String msg) {
		return playerEventFactory
				.createPlayerEndEvent(gameId, playerId, m, msg);
	}

}
