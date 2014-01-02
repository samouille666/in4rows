package in4rows.game;

import in4rows.model.GameReadable;

/**
 * @author ssayag
 * 
 * let replace the classic observable without being 
 * constrain by any implementation of the JDK
 * (jdk Observable = thread safe, heavy...)
 */
public interface ObservableGame {
	
	//TODO check useful
	public GameReadable getGame();
	
	public void addObs(GameObserver o);
	public void delObs(GameObserver o);
	public void notifyObs(GameEvent e);

}
