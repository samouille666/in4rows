package in4rows.event;

import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.Player;

public interface GameEvent {

	public enum Type {
		START, MOVE, DRAW, WIN, END,PRECEDING_MOVE_ERROR, OTHERS
	}

	public Type getType();

	public Move getLastMove();

	public String getMsg();
	
	public Player getPlayerToPlay();
	
	public Player getOpponent();
	
	public GameReadable getGame();
}
