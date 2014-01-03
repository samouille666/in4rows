package in4rows.model;

import in4rows.GridHelper;
import in4rows.strategy.GameStrategy;

public class DummyStrategy implements GameStrategy {


	private int col = 0;

	public DummyStrategy(int col) {
		this.col = col;
	}

	@Override
	public Move getMove(GameReadable g) {
		return new BasicMove(GridHelper.firstInCol_ModeCol(g, col));
	}

}
