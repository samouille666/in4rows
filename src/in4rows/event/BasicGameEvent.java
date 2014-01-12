package in4rows.event;

import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.Player;

public class BasicGameEvent implements GameEvent {

	private Type type;
	private Move m;
	private String msg;
	private Player toPlay;
	private Player opponent;
	private GameReadable game;

	public BasicGameEvent(Type type, GameReadable g, Move m, String msg,
			Player toPlay, Player opponent) {
		super();
		this.type = type;
		this.m = m;
		this.msg = msg;
		this.toPlay = toPlay;
		this.opponent = opponent;
		this.game = g;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Move getLastMove() {
		return m;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public Player getPlayerToPlay() {
		return toPlay;
	}

	@Override
	public Player getOpponent() {
		return opponent;
	}

	@Override
	public GameReadable getGame() {
		return game;
	}

	@Override
	public String toString() {
		return "BasicGameEvent [type=" + type + ", m=" + m + ", msg=" + msg
				+ ", toPlay=" + toPlay + ", opponent=" + opponent + ", game="
				+ game + "]";
	}
}
