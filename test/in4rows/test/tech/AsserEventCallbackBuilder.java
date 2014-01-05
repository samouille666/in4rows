package in4rows.test.tech;

import in4rows.In4RowsFactory;
import in4rows.game.BasicGameEvent;
import in4rows.game.GameEvent;
import in4rows.game.GameEvent.Type;
import in4rows.model.BasicMove;
import in4rows.model.BasicVertex;
import in4rows.model.Move;
import in4rows.model.Player;

import java.util.Deque;
import java.util.LinkedList;

public class AsserEventCallbackBuilder {

	private In4RowsFactory f;

	Deque<GameEvent> q;

	private int[] x;
	private int[] y;
	private Player p1;

	private GameEvent startingEvent;
	private GameEvent endingEvent;

	public AsserEventCallbackBuilder(int[] moveX, int[] moveY, Player p) {
		super();
		x = moveX;
		y = moveY;
		p1 = p;
	}

	public void setStartingEvent(Player p) {
		startingEvent = f.createStartEvent(p);
	}

	public void setEndingEvent(Player p, Move last) {
		endingEvent = f.createWinEvent(p, last);
	}

	public AssertEventCallBack getCallback() {
		q = new LinkedList<>();
		q.add(startingEvent);
		for (int i = 0; i < x.length; i++) {
			q.add(new BasicGameEvent(Type.MOVE, new BasicMove(new BasicVertex(
					y[i], x[i])), null, p1));
		}
		q.add(endingEvent);
		return new AssertEventCallbackImpl(q);
	}
}
