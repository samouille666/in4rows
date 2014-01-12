package in4rows;

import in4rows.event.BasicGameEvent;
import in4rows.event.EventDispatcher;
import in4rows.event.GameEvent;
import in4rows.game.BasicGame;
import in4rows.game.BasicObservableGame;
import in4rows.game.ObservableGame;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.BasicPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerTurn;
import in4rows.player.PlayerType;

public class In4RowsServerFactory implements In4RowsFactory {

	private EventDispatcher dispatcher;

	@Override
	public Move createMove(int col) {
		// TODO Auto-generated method stub
		return null;
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
}
