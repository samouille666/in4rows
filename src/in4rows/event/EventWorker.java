package in4rows.event;

import in4rows.game.GameObserver;
import in4rows.model.GameReadable;

import java.util.concurrent.Callable;

public class EventWorker implements Callable<Boolean>, Runnable {
	private GameObserver o;
	private GameReadable g;
	private GameEvent e;

	public EventWorker(GameObserver o, GameReadable g, GameEvent e) {
		super();
		this.o = o;
		this.g = g;
		this.e = e;
	}

	public Boolean call() throws Exception {
		o.update(g, e);
		return true;
	}

	@Override
	public void run() {
		o.update(g, e);
	}

}
