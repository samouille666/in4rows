package in4rows.game;

import in4rows.model.GameReadable;


public interface GameObserver {
	
	public void update(GameReadable gr, GameEvent e);

}
