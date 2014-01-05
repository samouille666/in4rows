package in4rows.player;

import in4rows.strategy.GameStrategy;

public abstract class AbstractPlayer implements ServerPlayer {

	protected GameStrategy s;

	private String id = null;

	public AbstractPlayer(String id) {
		this.id = id.toString();
	}

	public void setStrategy(GameStrategy s) {
		this.s = s;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "AbstractPlayer [id=" + id + "]";
	}
}
