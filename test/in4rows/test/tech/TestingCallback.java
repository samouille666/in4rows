package in4rows.test.tech;

import in4rows.game.GameEvent;
import in4rows.model.GameReadable;

public interface TestingCallback {
	public boolean toBeTested(GameReadable g, GameEvent evt) throws Exception;
}
