package in4rows;

import in4rows.model.Move;
import in4rows.player.BasicPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerType;

public class In4RowsServerFactory implements In4RowsFactory {
	@Override
	public Move createMove(int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player createPlayer(PlayerType type, String identifier) {
		return new BasicPlayer(identifier, type);
	}
	
}
