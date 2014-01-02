package in4rows.game;

import in4rows.model.Move;

public class BasicGameEvent implements GameEvent {

	private Type type;
	private Move m;
	private String msg;
	
	public BasicGameEvent(Type type, Move m, String msg) {
		super();
		this.type = type;
		this.m = m;
		this.msg = msg;
	}

	@Override
	public Type getType() {
		return type;
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
