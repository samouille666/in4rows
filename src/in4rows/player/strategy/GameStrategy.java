package in4rows.player.strategy;

import in4rows.model.GameReadable;
import in4rows.model.Move;

public interface GameStrategy {

	public enum Type {
		NAIVE, AGRESSIVE, BASIC, AVERAGE, EXPERIMENTED
	}

	public Move getMove(GameReadable g);

}
