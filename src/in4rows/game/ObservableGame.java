package in4rows.game;

import in4rows.model.GameRW;

/**
 * @author ssayag
 * 
 *         let replace the classic observable without being constrain by any
 *         implementation of the JDK (jdk Observable = thread safe, heavy...)
 */
public interface ObservableGame extends GameRW {

	public void attachObs(GameObserver o);

	public void detachObs(GameObserver o);

}
