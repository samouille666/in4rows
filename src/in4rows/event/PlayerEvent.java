package in4rows.event;

import in4rows.model.Move;

public interface PlayerEvent {

	public enum Type {
		MOVE, END
	}

	public Type getType();

	public Move getMove();

	public String getMsg();

}
