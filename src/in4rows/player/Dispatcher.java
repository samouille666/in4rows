package in4rows.player;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {

	private ExecutorService executor = Executors.newFixedThreadPool(3);

	public void executeEvent(EventWorker w) {
		executor.execute(w);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		executor.shutdown();
	}
}
