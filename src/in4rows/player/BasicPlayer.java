package in4rows.player;

public class BasicPlayer implements Player {
	private String id;
	private PlayerType type;

	public BasicPlayer(String id, PlayerType type) {
		super();
		this.id = id;
		this.type = type;
	}

	@Override
	public String getId() {
		return id;
	}

	public PlayerType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BasicPlayer other = (BasicPlayer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicPlayer [id=" + id + ", type=" + type + "]";
	}

}
