package in4rows.player.strategy;

import in4rows.In4RowsFactory;
import in4rows.helper.GridHelper;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.model.Vertex;

public class BasicStrategy implements GameStrategy {

	private In4RowsFactory f;

	@Override
	public Move getMove(GameReadable g) {
		Vertex v = GridHelper.firstInGame_ModeCol(g);
		return f.createMove(v.getCol());
	}

	public void setFactory(In4RowsFactory fact) {
		f = fact;
	}

}
