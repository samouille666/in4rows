package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;
import in4rows.player.ComputerPlayer;


public class DummyComputerPlayer extends ComputerPlayer {

	private GameReadable game;
	private GameEvent lastEvt;
		
	private GameObserver callback;

	public DummyComputerPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		game = gr;
		lastEvt = e;
		callback.update(gr, e);
		//the classical code after
		super.update(gr, e);
	}

	public GameEvent getLastEvent() {
		return lastEvt;
	}

	public GameReadable getLastGame() {
		return game;
	}

	public void setEventCallback(GameObserver c) {
		this.callback = c;
	}
}
