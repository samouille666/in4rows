package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;

public class DummyGameObserver implements GameObserver {
	private GameObserverCallBack callback;

	public DummyGameObserver() {
		// TODO Auto-generated constructor stub
	}

	public DummyGameObserver(GameObserverCallBack callback) {
		super();
		this.callback = callback;
	}

	public void setCallback(GameObserverCallBack callback) {
		this.callback = callback;
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		callback.execute(gr, e);
	}

}
