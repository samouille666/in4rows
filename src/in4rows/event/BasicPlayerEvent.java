package in4rows.event;

import in4rows.model.Move;

public class BasicPlayerEvent implements PlayerEvent {

	private PlayerEvent.Type t = null;
	private Move m = null;
	private String msg = null;
	private String pId = null;
	private String gameId = null;

	public BasicPlayerEvent(Type t, Move m, String msg, String pId,
			String gameId) {
		super();
		this.t = t;
		this.m = m;
		this.msg = msg;
		this.pId = pId;
		this.gameId = gameId;
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
	public String getPlayerId() {
		return pId;
	}

	@Override
	public String getGameId() {
		return gameId;
	}

	@Override
	public String toString() {
		return "BasicPlayerEvent [t=" + t + ", m=" + m + ", msg=" + msg
				+ ", pId=" + pId + ", gameId=" + gameId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
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
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
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
		if (pId == null) {
			if (other.pId != null)
				return false;
		} else if (!pId.equals(other.pId))
			return false;
		if (t != other.t)
			return false;
		return true;
	}

}
