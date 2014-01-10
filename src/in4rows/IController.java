package in4rows;

import in4rows.game.ObservableGame;
import in4rows.model.PlayerType;

public interface IController {
	
	public ObservableGame openGame(PlayerType p1, PlayerType p2);

}
