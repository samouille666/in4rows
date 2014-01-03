package in4rows.model;

import in4rows.game.GameEvent;
import in4rows.player.ComputerPlayer;

public class DummyComputerPlayer  extends ComputerPlayer {

	private GameReadable game;

	private GameEvent lastEvt;

	public DummyComputerPlayer(String id) {
		super(id);
	}

	private AssertEventCallBack c;
	
	@Override
	public void update(GameReadable gr, GameEvent e) {
		super.update(gr, e);
		game = gr;
		lastEvt = e;
		c.assertEvent(e);
	}

	public GameEvent getLastEvent() {
		return lastEvt;
	}

	public GameReadable getLastGame() {
		return game;
	}	
	
	public void setEventCallback(AssertEventCallBack c){
		
	}
}
