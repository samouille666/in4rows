package in4rows.player;

import in4rows.game.GameEvent;
import in4rows.model.GameReadable;

public class EventWorker implements Runnable {
	private ServerPlayer p;
	private GameReadable g;
	private GameEvent e;

	public EventWorker(ServerPlayer p, GameReadable g, GameEvent e) {
		super();
		this.p = p;
		this.g = g;
		this.e = e;
	}

	@Override
	public void run() {
		p.update(g, e);
	}

}
