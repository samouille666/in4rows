package in4rows.test.tech;

import in4rows.model.BasicMove;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.strategy.GameStrategy;

import java.util.ArrayDeque;
import java.util.Deque;

public class DummyStrategy implements GameStrategy {

	private Deque<Move> q;

	public DummyStrategy() {
	}

	@Override
	public Move getMove(GameReadable g) {
		if (q != null)
			return q.poll();
		return null;
	}

	public void setMoves(int[] rows, int[] col) {
		q = new ArrayDeque<>(rows.length);
		for (int i = 0; i < rows.length; i++) {
			q.add(new BasicMove(rows[i], col[i]));
		}
	}

}
