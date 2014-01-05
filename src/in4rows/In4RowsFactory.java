package in4rows;

import in4rows.event.BasicGameEvent;
import in4rows.event.EventDispatcher;
import in4rows.event.GameEvent;
import in4rows.event.GameEvent.Type;
import in4rows.game.BasicGame;
import in4rows.model.BasicMove;
import in4rows.model.Disk;
import in4rows.model.GameWritable;
import in4rows.model.Move;
import in4rows.model.Player;
import in4rows.model.PlayerTurn;
import in4rows.player.HumanPlayer;
import in4rows.player.ServerPlayer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class In4RowsFactory {

	@Autowired
	private EventDispatcher dispatcher;

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
		return new BasicGame(width, height, p1, p1c, p1t, this);
	}

	public GameEvent createStartEvent(Player toPlay, Player opponent) {
		return new BasicGameEvent(Type.START, null, null, toPlay, opponent);
	}

	public GameEvent createMoveEvent(Player toPlay, Player opponent, Move last) {
		return new BasicGameEvent(GameEvent.Type.MOVE, last, opponent.getId()
				+ " has moved.", toPlay, opponent);
	}

	public Move createMove(int row, int col) {
		return new BasicMove(row, col);
	}
	public GameEvent createDrawEvent(Player p1, Player p2, Move last) {
		String msg = "Player " + p1.getId() + " and player " + p2.getId()
				+ " are draw";
		return new BasicGameEvent(GameEvent.Type.DRAW, last, msg, null, null);
	}

	public GameEvent createWinEvent(Player winner) {
		String msg = "The player " + winner.getId()
				+ " has won the game.";
		return new BasicGameEvent(GameEvent.Type.WIN, null, msg, null, winner);
	}

	public GameEvent createErrorEvent(Player toPlay, Player opponent) {
		return new BasicGameEvent(GameEvent.Type.PRECEDING_MOVE_ERROR, null,
				"Impossible move !", toPlay, opponent);
	}

	public EventDispatcher createEventDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(EventDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
}
