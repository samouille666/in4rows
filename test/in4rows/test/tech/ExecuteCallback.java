package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.model.GameReadable;
import in4rows.model.Player;

public interface ExecuteCallback {
	public boolean toBeTested(GameReadable g, GameEvent evt, Player p);
}
