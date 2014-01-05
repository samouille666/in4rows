package in4rows.test.tech;

import in4rows.In4RowsFactory;
import in4rows.event.BasicGameEvent;
import in4rows.event.GameEvent;
import in4rows.event.GameEvent.Type;
import in4rows.model.BasicMove;
import in4rows.model.BasicVertex;
import in4rows.model.Player;

import java.util.Deque;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

public class AssertEventCallbackBuilder {

	@Autowired
	private In4RowsFactory f;

	Deque<GameEvent> q;

	private int[] row;
	private int[] col;
	private Player p;

	private GameEvent startingEvent;
	private GameEvent endingEvent;

	public AssertEventCallbackBuilder() {

	}

	public void setStartingEvent(Player p) {
		startingEvent = f.createStartEvent(p, null);
	}

	public void setEndingEvent(Player p) {
		endingEvent = f.createWinEvent(p);
	}

	public void addStartingEvent() {
		if (startingEvent != null)
			q.add(startingEvent);
	}

	public void addEndingEvent(Player p) {
		if (endingEvent != null)
			endingEvent = f.createWinEvent(p);
	}

	public GameObserverCallBack getCallback() {
		q = new LinkedList<>();

		addStartingEvent();

		for (int i = 0; i < row.length; i++) {
			q.add(f.createMoveEvent(null, p, f.createMove(row[i], col[i])));
		}

		addEndingEvent(p);

		return new AssertEventCallbackImpl(q);
	}

	public void setRow(int[] row) {
		this.row = row;
	}

	public void setCol(int[] col) {
		this.col = col;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public void setBuildingElement(int[] rows, int[] cols, Player p) {
		setP(p);
		setCol(cols);
		setRow(rows);
	}
}
