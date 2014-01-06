package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;
import in4rows.model.Player;

public class OnePlayerMovesTestCallback implements GameObserver {

	private Player p = null;

	public OnePlayerMovesTestCallback() {
	}
	
	public OnePlayerMovesTestCallback(Player p) {
		setPlayer(p);
	}

	@Override
	public void update(GameReadable g, GameEvent evt) {
		if (evt.getLastMove() != null
				&& !evt.getPlayerToPlay().getId().equals(p.getId()))
			return;
	}

	public void setPlayer(Player p) {
		this.p = p;
	}

}
