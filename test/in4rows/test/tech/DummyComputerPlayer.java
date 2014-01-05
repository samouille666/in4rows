package in4rows.test.tech;

import org.springframework.beans.factory.annotation.Autowired;

import in4rows.event.GameEvent;
import in4rows.model.GameReadable;
import in4rows.player.ComputerPlayer;

public class DummyComputerPlayer extends ComputerPlayer {

	private GameReadable game;
	private GameEvent lastEvt;
		
	private GameObserverCallBack assertCallback;
	
	@Autowired
	private ExecuteCallback testCallback;

	public DummyComputerPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		game = gr;
		lastEvt = e;
		if (testCallback.toBeTested(gr, e, this))
			assertCallback.execute(gr, e);
		//the classical code after
		super.update(gr, e);
	}

	public GameEvent getLastEvent() {
		return lastEvt;
	}

	public GameReadable getLastGame() {
		return game;
	}

	public void setEventCallback(GameObserverCallBack c) {
		this.assertCallback = c;
	}

	public void setEventTestingCondition(ExecuteCallback c) {
		testCallback = c;
	}
}
