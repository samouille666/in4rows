package in4rows.game;

import in4rows.model.GameReadable;

import java.util.Observer;


public interface GameObserver extends Observer {
	
	public void update(GameReadable gr, GameEvent e);

}
