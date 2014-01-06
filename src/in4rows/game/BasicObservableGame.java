package in4rows.game;

import in4rows.event.EventDispatcher;
import in4rows.event.EventWorker;
import in4rows.event.GameEvent;
import in4rows.model.GameReadable;
import in4rows.model.PlayerTurn;
import in4rows.player.Dispatchable;
import in4rows.player.PlayerInGame;

import java.util.ArrayList;
import java.util.List;

public class BasicObservableGame implements ObservableGame, Dispatchable {

	private EventDispatcher dispatcher;
	// we just want the observer to be able to read the game
	private GameReadable g;
	// not player observers
	private ArrayList<GameObserver> observers = new ArrayList<>();

	private boolean changed = false;

	private PlayerInGame p1;
	private PlayerInGame p2;

	public BasicObservableGame(GameReadable g, EventDispatcher dispatcher) {
		this.g = g;
		this.dispatcher = dispatcher;
	}

	@Override
	public void setEventDispatcher(EventDispatcher d) {
		dispatcher = d;
	}

	@Override
	public GameReadable getGame() {
		return g;
	}

	@Override
	public void attachObs(GameObserver o) {
		synchronized (observers) {
			observers.add(o);
		}
	}

	@Override
	public void detachObs(GameObserver o) {
		observers.remove(o);
	}

	public void notifyObs(GameEvent e) {
		if (!changed)
			return;

		List<EventWorker> l = new ArrayList<>();
		for (GameObserver o : observers)
			l.add(new EventWorker(o, g, e));
		l.add(new EventWorker(PlayerTurn.NO.equals(p1) ? p1 : p2, g, e));
		dispatcher.executeUntilEnd(l);
		dispatcher.executeEvent(new EventWorker(PlayerTurn.YES.equals(p1) ? p1 : p2,
				g, e));
		changed = false;
	}

	public void setChanged() {
		changed = true;
	}

	public void setP1(PlayerInGame p1) {
		this.p1 = p1;
	}

	public void setP2(PlayerInGame p2) {
		this.p2 = p2;
	}

}
