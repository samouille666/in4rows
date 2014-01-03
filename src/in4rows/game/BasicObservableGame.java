package in4rows.game;

import in4rows.model.GameReadable;
import in4rows.player.Dispatchable;
import in4rows.player.EventDispatcher;
import in4rows.player.EventWorker;

import java.util.Vector;

public class BasicObservableGame implements ObservableGame, Dispatchable {

	//to dispatch the game events to observers
	EventDispatcher dispatcher;
	//we just want the observer to be able to read the game
	private GameReadable g;
	
	// vector is synchronized => when notifying does not let update vector
	private Vector<GameObserver> observers = new Vector<>();

	private boolean changed = false;

	public BasicObservableGame(GameReadable g) {
		this.g = g;
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
		observers.add(o);
	}

	@Override
	public void detachObs(GameObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObs(GameEvent e) {
		if (!changed)
			return;
		for (GameObserver o : observers) 
			dispatcher.executeEvent(new EventWorker(o, g, e));		
	}
	
	public void setChanged() {
		changed = true;
	}
}
