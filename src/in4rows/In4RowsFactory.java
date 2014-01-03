package in4rows;

import in4rows.game.BasicGame;
import in4rows.game.BasicGameEvent;
import in4rows.game.GameEvent;
import in4rows.game.GameEvent.Type;
import in4rows.model.Disk;
import in4rows.model.GameWritable;
import in4rows.model.Move;
import in4rows.model.Player;
import in4rows.model.PlayerTurn;
import in4rows.player.EventDispatcher;
import in4rows.player.HumanPlayer;
import in4rows.player.ServerPlayer;

import java.util.UUID;

public class In4RowsFactory {
	public In4RowsFactory() {
	}

	public Player createPlayer() {
		return new HumanPlayer(UUID.randomUUID().toString());
	}

	public ServerPlayer createServerPlayer() {
		return new HumanPlayer(UUID.randomUUID().toString());
	}

	public GameWritable createGame(ServerPlayer p1) {
		return createGame(p1, PlayerTurn.YES);
	}

	public GameWritable createGame(ServerPlayer p1, PlayerTurn p1t) {
		return createGame(p1, Disk.BLACK, p1t);
	}

	public GameWritable createGame(ServerPlayer p1, Disk p1c) {
		return createGame(p1, p1c, PlayerTurn.YES);
	}

	public GameWritable createGame(ServerPlayer p1, Disk p1c, PlayerTurn p1t) {
		return createGame(10, 10, p1, p1c, p1t);
	}

	public GameWritable createGame(int width, int height, ServerPlayer p1,
			Disk p1c, PlayerTurn p1t) {
		return new BasicGame(width, height, p1, p1c, p1t);
	}

	public GameEvent createStartEvent(Player p) {
		return new BasicGameEvent(Type.START, null, null, p);
	}

	public GameEvent createMoveEvent(Player p, Move last) {
		return new BasicGameEvent(GameEvent.Type.MOVE, last, p.getId()
				+ " has moved.", p);
	}

	public GameEvent createDrawEvent(Player p1, Player p2) {
		String msg = "Player " + p1.getId() + " and player " + p2.getId() + " are draw";
		return new BasicGameEvent(GameEvent.Type.DRAW, last, msg, p);
	}

	public GameEvent createWinEvent(Player playerInTurn, Move last) {
		String msg = "The player " + playerInTurn.getId() + " has won the game.";
		return new BasicGameEvent(GameEvent.Type.WIN, last, msg, playerInTurn);
	}

	public GameEvent createErrorEvent(Player p) {
		return new BasicGameEvent(GameEvent.Type.PRECEDING_MOVE_ERROR, null,
				"Impossible move !", p);
	}

	public EventDispatcher createEventDispatcher() {
		return new EventDispatcher();
	}

}
