package in4rows.player;

import in4rows.event.GameEvent;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.PlayerTurn;

public class PlayerInGame implements ServerPlayer{

	private ServerPlayer p = null;
	
	private Disk color = null;
	
	private PlayerTurn turn = null;
	
	public PlayerInGame(ServerPlayer p) {
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

	@Override
	public void addObs(PlayerObserver o) {
		p.addObs(o);
	}
	
	@Override
	public void delObs(PlayerObserver o) {
		p.delObs(o);
	}
	
	@Override
	public void update(GameReadable gr, GameEvent e) {
		p.update(gr, e);
	}
	
	@Override
	public String toString() {
		return "PlayerInGame [p=" + p + ", color=" + color + ", turn=" + turn
				+ "]";
	}

}
