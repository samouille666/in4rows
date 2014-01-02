package in4rows.game;

import in4rows.model.Move;

public interface GameEvent {

	public enum Type {
		START, MOVE, DRAW, WIN, P1_WIN, P2_WIN, PRECEDING_MOVE_ERROR, OTHERS
	}

	public Type getType();

	public Move getMove();

	public String getMsg();

}
