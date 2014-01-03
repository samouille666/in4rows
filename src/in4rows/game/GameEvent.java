package in4rows.game;

import in4rows.model.Move;
import in4rows.model.Player;

public interface GameEvent {

	public enum Type {
		START, MOVE, DRAW, WIN, PRECEDING_MOVE_ERROR, OTHERS
	}

	public Type getType();

	public Move getMove();

	public String getMsg();
	
	public Player getPlayer();
}
