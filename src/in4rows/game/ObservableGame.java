package in4rows.game;


/**
 * @author ssayag
 * 
 * let replace the classic observable without being 
 * constrain by any implementation of the JDK
 * (jdk Observable = thread safe, heavy...)
 */
public interface ObservableGame {
		
	public void attachObs(GameObserver o);
	public void detachObs(GameObserver o);

}
