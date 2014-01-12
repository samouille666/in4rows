package in4rows.player;

import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.strategy.GameStrategy;

public interface ComputerPlayer extends Player {
	public void setStrategy(GameStrategy strategy);

	public Move getMove(GameReadable g, Disk colorToPlay);
}
