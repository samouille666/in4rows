package in4rows.event;

import in4rows.model.Move;
import in4rows.model.Player;

public class BasicGameEvent implements GameEvent {

	private Type type;
	private Move m;
	private String msg;
	private Player toPlay;
	private Player opponent;

	public BasicGameEvent(Type type, Move m, String msg, Player toPlay, Player opponent) {
		super();
		this.type = type;
		this.m = m;
		this.msg = msg;
		this.toPlay = toPlay;
		this.opponent = opponent;
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
}
