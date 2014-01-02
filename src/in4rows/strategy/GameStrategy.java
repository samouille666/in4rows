package in4rows.strategy;

import in4rows.model.GameReadable;
import in4rows.model.Move;

public interface GameStrategy {

	public enum Style {
		NAIF, AGRESSIVE, TEMPERED, HUMAN
	}
	
	Move getMove(GameReadable g);	

}
