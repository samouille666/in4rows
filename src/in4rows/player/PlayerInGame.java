package in4rows.player;

import in4rows.model.Disk;
import in4rows.model.Player;
import in4rows.model.PlayerTurn;

public class PlayerInGame implements Player {

	private Player p = null;
	
	private Disk color = null;
	
	private PlayerTurn turn = null;
	
	public PlayerInGame(Player p) {
		this.p = p;
	}
	
	@Override
	public String getId() {
		return p.getId();
	}

	public Disk getColor() {
		return color;
	}

	public void setColor(Disk color) {
		this.color = color;
	}

	public PlayerTurn getTurn() {
		return turn;
	}

	public void setTurn(PlayerTurn turn) {
		this.turn = turn;
	}

}
