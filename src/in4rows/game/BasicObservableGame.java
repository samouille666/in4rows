package in4rows.game;

import in4rows.model.GameReadable;

import java.util.Observable;

public class BasicObservableGame extends Observable implements ObservableGame {

	private GameReadable g ;
	
	public BasicObservableGame(GameReadable g) {
		this.g = g;
	}
	
	@Override
	public GameReadable getGame() {
		return g;
	}
	
	@Override
	public void addObs(GameObserver o) {
		addObserver(o);
	}

	@Override
	public void delObs(GameObserver o) {
		deleteObserver(o);
	}	
	
	@Override
	public void notifyObs(GameEvent e) {
		//TODO refine mechanism
		notifyObservers(e);
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO refine mechanism				
		super.notifyObservers(arg);
	}

	public void setChanged(){
		//TODO protected have to reimplement
		super.setChanged();
	}

}
