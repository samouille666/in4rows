package in4rows.event;

import in4rows.game.GameObserver;
import in4rows.model.GameReadable;

public class EventWorker implements Runnable {
	private GameObserver o;
	private GameReadable g;
	private GameEvent e;

	public EventWorker(GameObserver o, GameReadable g, GameEvent e) {
		super();
		this.o = o;
		this.g = g;
		this.e = e;
	}

	@Override
	public void run() {
		o.update(g, e);
	}

}
