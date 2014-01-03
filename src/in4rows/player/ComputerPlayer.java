package in4rows.player;

import in4rows.game.GameEvent;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.PlayerEvent.Type;

public class ComputerPlayer extends AbstractPlayer {

	private PlayerObserver po;

	public ComputerPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
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
