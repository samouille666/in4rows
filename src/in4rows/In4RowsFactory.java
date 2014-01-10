package in4rows;

import in4rows.model.Move;
import in4rows.player.Player;
import in4rows.player.PlayerType;

public interface In4RowsFactory {
	
	public Player createPlayer(PlayerType type, String identifier);
	
	public Move createMove(int col);

}
