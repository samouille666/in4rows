package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.model.GameReadable;

public interface GameObserverCallBack {

	public void execute(GameReadable g, GameEvent e);

}
