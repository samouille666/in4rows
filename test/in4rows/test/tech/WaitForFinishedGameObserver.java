package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.model.GameReadable;

public class WaitForFinishedGameObserver implements GameObserverCallBack {
	
	private boolean gameFinished = false;
	
	@Override
	public void execute(GameReadable g, GameEvent e) {
		if (GameEvent.Type.WIN.equals(e.getType()) || GameEvent.Type.DRAW.equals(e.getType())) 
			gameFinished = true;
	}

	public boolean isGameFinished() {
		return gameFinished;
	}	
}
