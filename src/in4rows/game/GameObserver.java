package in4rows.game;

import in4rows.model.GameReadable;

import java.util.Observer;


public interface GameObserver {
	
	public void update(GameReadable gr, GameEvent e);

}
