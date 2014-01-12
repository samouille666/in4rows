package in4rows.player;

import in4rows.model.Disk;

public class BasicPlayerInGame implements Player, PlayerInGame {

	private Player p = null;

	private Disk color = null;

	private PlayerTurn turn = null;

	public BasicPlayerInGame(Player p, Disk color, PlayerTurn turn) {
		super();
		this.p = p;
		this.color = color;
		this.turn = turn;
	}

	@Override
	public String getId() {
		return p.getId();
	}

	@Override
	public Disk getColor() {
		return color;
	}

	public void setColor(Disk color) {
		this.color = color;
	}

	@Override
	public PlayerTurn getTurn() {
		return turn;
	}

	@Override
	public void setTurn(PlayerTurn turn) {
		this.turn = turn;
	}

	@Override
	public String toString() {
		return "BasicPlayerInGame [p=" + p + ", color=" + color + ", turn="
				+ turn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + ((turn == null) ? 0 : turn.hashCode());
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
		BasicPlayerInGame other = (BasicPlayerInGame) obj;
		if (color != other.color)
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (turn != other.turn)
			return false;
		return true;
	}

}
