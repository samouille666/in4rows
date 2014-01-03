package in4rows.model;

import in4rows.In4RowsFactory;
import in4rows.game.BasicGameEvent;
import in4rows.game.GameEvent;
import in4rows.game.GameEvent.Type;

import java.security.acl.LastOwnerException;
import java.util.Deque;
import java.util.LinkedList;

public class AsserEventCallbackBuilder {

	private In4RowsFactory f;

	Deque<GameEvent> q;

	private int[] x;
	private int[] y;
	private Player p1;

	private GameEvent startingEvent;

	public AsserEventCallbackBuilder(int[] moveX, int[] moveY, Player p) {
		super();
		x = moveX;
		y = moveY;
		p1 = p;
	}

	public void setStartingEvent(Player p) {
		q.add(f.createStartEvent(p));
	}

	public void setEndingEvent(Player p, Move last) {
		q.add(f.createWinEvent(p, last));
	}

	public AssertEventCallBack getCallback() {
		q = new LinkedList<>();
		q.add(new BasicGameEvent(Type.START, null, null, p1));

		for (int i = 0; i < x.length; i++) {
			q.add(new BasicGameEvent(Type.MOVE, new BasicMove(new BasicVertex(
					y[i], x[i])), null, p1));
		}

		return null;
	}
}
