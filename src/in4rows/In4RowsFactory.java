package in4rows;

import in4rows.model.Move;
import in4rows.model.Player;

public interface In4RowsFactory {
	
	public Player createPlayer(String identifier);
	
	public Move createMove(int col);

}
