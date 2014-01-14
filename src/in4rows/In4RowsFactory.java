package in4rows;

import in4rows.event.GameEvent;
import in4rows.game.ObservableGame;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.ComputerPlayer;
import in4rows.player.GameObserverComputerPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerTurn;
import in4rows.player.PlayerType;
import in4rows.player.strategy.GameStrategy;
import in4rows.player.strategy.GameStrategy.Type;

public interface In4RowsFactory {

	public Player createHumanPlayer(PlayerType type, String identifier);

	public ObservableGame createGame(Player p1, Player p2, Disk color,
			PlayerTurn t, int width, int height);

	public ObservableGame createGame(Player p1, Player p2, Disk color,
			PlayerTurn t);

	public ObservableGame createGame(Player p1, Player p2, Disk color);

	public ObservableGame createGame(Player p1, Player p2, PlayerTurn t);

	public ObservableGame createGame(Player p1, Player p2);

	public GameEvent createStartEvent(GameReadable g);

	public GameEvent createEndEvent(GameReadable g);

	public GameStrategy createStrategy(GameStrategy.Type t);

	public ComputerPlayer createMachinePlayer(Type t, IController controller, GameStopper s);
	
	public GameObserverComputerPlayer createObserverMachinePlayer(Type t, IController controller, GameStopper s);

	public Move createMove(int col);

}
