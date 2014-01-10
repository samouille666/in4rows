package in4rows.event;

import in4rows.model.Move;
import in4rows.model.Player;

public class BasicGameEvent implements GameEvent {

	private Type type;
	private Move m;
	private String msg;
	private Player toPlay;
	private Player opponent;

	public BasicGameEvent(Type type, Move m, String msg, Player toPlay,
			Player opponent) {
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

	@Override
	public String toString() {
		return "BasicGameEvent [type=" + type + ", m=" + m + ", msg=" + msg
				+ ", toPlay=" + toPlay + ", opponent=" + opponent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result
				+ ((opponent == null) ? 0 : opponent.hashCode());
		result = prime * result + ((toPlay == null) ? 0 : toPlay.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicGameEvent other = (BasicGameEvent) obj;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (opponent == null) {
			if (other.opponent != null)
				return false;
		} else if (!opponent.equals(other.opponent))
			return false;
		if (toPlay == null) {
			if (other.toPlay != null)
				return false;
		} else if (!toPlay.equals(other.toPlay))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
