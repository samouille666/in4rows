package in4rows.player;


/**
 * @author ssayag
 * 
 * let replace the classic observable without being 
 * constrain by any implementation of the JDK
 * (jdk Observable = thread safe, heavy...)
 */
public interface ObservablePlayer {
	
	public void addObs(PlayerObserver o);
	public void delObs(PlayerObserver o);

}
