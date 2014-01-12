package in4rows.player;

import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.strategy.GameStrategy;

public class BasicComputerPlayer implements ComputerPlayer {

	private String id;
	private GameStrategy strategy;

	public BasicComputerPlayer(String id, GameStrategy strategy) {
		super();
		this.id = id;
		this.strategy = strategy;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setStrategy(GameStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public PlayerType getType() {
		return PlayerType.MACHINE;
	}

	@Override
	public Move getMove(GameReadable g, Disk colorToPlay) {
		return strategy.getMove(g, colorToPlay);
	}
}
