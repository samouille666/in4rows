package in4rows.event;

import in4rows.model.Move;
import in4rows.model.Player;

public class BasicPlayerEvent implements PlayerEvent {

	private PlayerEvent.Type t = null;
	private Move m = null;
	private String msg = null;
	private Player p = null;

	public BasicPlayerEvent(Type t, Move m, String msg, Player p) {
		super();
		this.t = t;
		this.m = m;
		this.msg = msg;
		this.p = p;
	}

	@Override
	public Type getType() {
		return t;
	}

	@Override
	public Move getMove() {
		return m;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public Player getPlayer() {
		return p;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
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
		BasicPlayerEvent other = (BasicPlayerEvent) obj;
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
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (t != other.t)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicPlayerEvent [t=" + t + ", m=" + m + ", msg=" + msg
				+ ", p=" + p + "]";
	}

}
