package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;

public class SilentCallBack implements GameObserver {
	public void update(GameReadable gr, GameEvent e) {
		return;
	}
}
