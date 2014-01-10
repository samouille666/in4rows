package in4rows.player;

import in4rows.model.Disk;
import in4rows.model.Player;
import in4rows.model.PlayerTurn;

public interface PlayerInGame extends Player {

	public Disk getColor();

	public PlayerTurn getTurn();
	
	public void setTurn(PlayerTurn turn);

}