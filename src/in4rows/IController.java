package in4rows;

import in4rows.exception.ExistingPlayerException;
import in4rows.game.ObservableGame;
import in4rows.player.Player;
import in4rows.player.PlayerType;

public interface IController {
	
	public ObservableGame openGame(PlayerType p1, PlayerType p2);
	
	public Player createPlayer(PlayerType t, String id) throws ExistingPlayerException;

}
