package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;

public class NotExecuteCallback implements GameObserver {

	public NotExecuteCallback() {
	}
	
	@Override
	public void update(GameReadable g, GameEvent evt) {
		return;
	}

}
