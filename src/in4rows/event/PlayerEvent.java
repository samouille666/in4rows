package in4rows.event;

import in4rows.model.Move;
import in4rows.player.Player;

public interface PlayerEvent {

	public enum Type {
		MOVE, END
	}
	
	public Player getPlayer();

	public Type getType();

	public Move getMove();

	public String getMsg();

}
