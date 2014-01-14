package in4rows.event;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventDispatcher {

	public void executeEvent(EventWorker w) {
		ExecutorService s = Executors.newFixedThreadPool(2);
		s.execute(w);
		while (!s.isTerminated())
			;
	}

	public void executeUntilEnd(List<EventWorker> l) {
		ExecutorService s = Executors.newFixedThreadPool(1);
		ExecutorCompletionService<Boolean> exec = new ExecutorCompletionService<>(
				s);
		for (EventWorker eventWorker : l) {
			exec.submit(eventWorker);
		}

		for (int i = 0; i < l.size(); i++) {
			try {
				exec.take().get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		s.shutdown();
	}
}
