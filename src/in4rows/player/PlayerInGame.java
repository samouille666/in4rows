package in4rows.player;

import in4rows.model.Disk;

public interface PlayerInGame extends Player {

	public Disk getColor();

	public PlayerTurn getTurn();
	
	public void setTurn(PlayerTurn turn);

}