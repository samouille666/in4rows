package in4rows.player.strategy;

import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Move;

public interface GameStrategy {
	
	public Move getMove(GameReadable g, Disk colorToPlay);

}
