package in4rows.event;

import in4rows.event.GameEvent.Type;
import in4rows.model.Move;
import in4rows.model.Player;

public class BasicGameEvent implements GameEvent {

	private Type type;
	private Move m;
	private String msg;
	private Player p;

	public BasicGameEvent(Type type, Move m, String msg, Player p) {
		super();
		this.type = type;
		this.m = m;
		this.msg = msg;
		this.p = p;
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
	
	@Override
	public Player getPlayer() {
		return p;
	}
}
