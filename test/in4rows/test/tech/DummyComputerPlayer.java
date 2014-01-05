package in4rows.test.tech;

import in4rows.game.GameEvent;
import in4rows.model.GameReadable;
import in4rows.player.ComputerPlayer;

public class DummyComputerPlayer extends ComputerPlayer {

	private GameReadable game;
	private GameEvent lastEvt;
	private AssertEventCallBack c;
	
	public DummyComputerPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		super.update(gr, e);
		game = gr;
		lastEvt = e;

	}

	public GameEvent getLastEvent() {
		return lastEvt;
	}

	public GameReadable getLastGame() {
		return game;
	}

	public void setEventCallback(AssertEventCallBack c) {
		this.c = c; 
	}
	
	public void setEventTestingCondition(){
		
	}
}
