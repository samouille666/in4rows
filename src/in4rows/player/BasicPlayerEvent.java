package in4rows.player;

import in4rows.model.Move;

public class BasicPlayerEvent implements PlayerEvent {

	private PlayerEvent.Type t = null;
	private Move m = null;
	private String msg = null;

	public BasicPlayerEvent(Type t, Move m, String msg) {
		super();
		this.t = t;
		this.m = m;
		this.msg = msg;
	}

	@Override
	public Type getType() {
		return t;
	}

	@Override
	public Move getMove() {
		return m;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
