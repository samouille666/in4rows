package in4rows;

import in4rows.event.PlayerEvent;
import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.player.Player;
import in4rows.player.PlayerType;
import in4rows.player.strategy.GameStrategy;

import java.util.List;

public interface IController {

	public ObservableGame openGame(Player p1, Player p2, List<GameObserver> l)
			throws GameNotProperlyInitializedException;

	public void openGame(Player p1,
			GameStrategy.Type machineStrategy, List<GameObserver> l)
			throws GameNotProperlyInitializedException;

	public Player createPlayer(PlayerType t, String id)
			throws ExistingPlayerException;
	
	public void playMove(PlayerEvent e);

}
