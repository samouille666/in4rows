package in4rows.test.tech;

import org.springframework.beans.factory.annotation.Autowired;

import in4rows.event.GameEvent;
import in4rows.model.GameReadable;
import in4rows.player.ComputerPlayer;

public class DummyComputerPlayer extends ComputerPlayer {

	private GameReadable game;
	private GameEvent lastEvt;
		
	private AssertEventCallBack assertCallback;
	
	@Autowired
	private TestingCallback testCallback;

	public DummyComputerPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		game = gr;
		lastEvt = e;
		if (testCallback.toBeTested(gr, e, this))
			assertCallback.assertEvent(e);
		//the classical code after
		super.update(gr, e);
	}

	public GameEvent getLastEvent() {
		return lastEvt;
	}

	public GameReadable getLastGame() {
		return game;
	}

	public void setEventCallback(AssertEventCallBack c) {
		this.assertCallback = c;
	}

	public void setEventTestingCondition(TestingCallback c) {
		testCallback = c;
	}
}
