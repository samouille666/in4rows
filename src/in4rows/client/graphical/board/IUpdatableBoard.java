package in4rows.client.graphical.board;

import in4rows.model.GameReadable;

public interface IUpdatableBoard {

	public void setGame(GameReadable g);
	
	public GameReadable getGame();

}
