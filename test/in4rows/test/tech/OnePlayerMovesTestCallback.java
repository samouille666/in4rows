package in4rows.test.tech;

import in4rows.game.GameEvent;
import in4rows.model.GameReadable;
import in4rows.model.Player;

public class OnePlayerMovesTestCallback implements TestingCallback {

	private Player p;

	public OnePlayerMovesTestCallback(Player p) {
		super();
		this.p = p;
	}

	@Override
	public boolean toBeTested(GameReadable g, GameEvent evt) throws Exception {
		if(evt == null || evt.getPlayer() == null)
			throw new Exception("event witout player");
		return p.getId().equals(evt.getPlayer().getId());
	}

}
