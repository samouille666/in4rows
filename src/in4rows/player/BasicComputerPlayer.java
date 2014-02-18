package in4rows.player;

import in4rows.IController;
import in4rows.In4RowsServerFactory;
import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.strategy.GameStrategy;

public class BasicComputerPlayer implements GameObserverComputerPlayer {

	private IController c;
	private In4RowsServerFactory f;

	private String id;
	private GameStrategy strategy;

	public BasicComputerPlayer(String id, GameStrategy strategy) {
		super();
		this.id = id;
		this.strategy = strategy;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setStrategy(GameStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public PlayerType getType() {
		return PlayerType.MACHINE;
	}

	@Override
	public Move getMove(GameReadable g, Disk colorToPlay) {
		return strategy.getMove(g);
	}

	@Override
	public String toString() {
		return "BasicComputerPlayer [id=" + id + ", strategy=" + strategy + "]";
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		if (!getId().equals(gr.playerToPlay().getId())
				|| GameEvent.Type.WIN.equals(e.getType())
				|| GameEvent.Type.DRAW.equals(e.getType()))
			return;
		PlayerEvent evt = f.createPlayerMoveEvent(gr.getId(), getId(),
				strategy.getMove(gr), "Computer play.");
		try {
			c.playMove(evt);
		} catch (ErroneousPlayerEventException e1) {
		}
	}

	public void setController(IController controller) {
		c = controller;
	}

	public void setFactory(In4RowsServerFactory f) {
		this.f = f;
	}

}
