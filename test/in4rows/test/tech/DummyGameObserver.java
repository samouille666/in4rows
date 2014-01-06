package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;

public class DummyGameObserver implements GameObserver {
	private GameObserver callback;

	public DummyGameObserver() {
		// TODO Auto-generated constructor stub
	}

	public DummyGameObserver(GameObserver callback) {
		super();
		this.callback = callback;
	}

	public void setCallback(GameObserver callback) {
		this.callback = callback;
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		callback.update(gr, e);
	}

}
