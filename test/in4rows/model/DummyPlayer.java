package in4rows.model;

import in4rows.game.GameEvent;
import in4rows.player.AbstractPlayer;
import in4rows.player.PlayerObserver;

public class DummyPlayer  extends AbstractPlayer{

	private GameReadable game;

	private GameEvent evt;

	public DummyPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		game = gr;
		evt = e;
	}

	public GameEvent getLastEvent() {
		return evt;
	}

	public GameReadable getLastGame() {
		return game;
	}

	@Override
	public void addObs(PlayerObserver o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delObs(PlayerObserver o) {
		// TODO Auto-generated method stub

	}
}
