package in4rows.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventDispatcher {

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
