package in4rows.player;

import in4rows.event.BasicPlayerEvent;
import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent.Type;
import in4rows.model.GameReadable;
import in4rows.model.Move;

public class ComputerPlayer extends AbstractPlayer {

	private PlayerObserver po;

	public ComputerPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		// check if it is its turn to play
		if (!this.getId().equals(e.getPlayerToPlay().getId()))
			return;
		Move mv = s.getMove(gr);
		po.update(this, new BasicPlayerEvent(Type.MOVE, mv, ""));
	}

	@Override
	public void addObs(PlayerObserver o) {
		po = o;
	}

	@Override
	public void delObs(PlayerObserver o) {
		po = null;
	}
}
