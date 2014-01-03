package in4rows.player;

import in4rows.model.Player;

public interface PlayerObserver {
	
	public void update(Player p, PlayerEvent e);

}
