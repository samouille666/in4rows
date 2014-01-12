package in4rows;

import java.util.List;

import in4rows.exception.ExistingPlayerException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.game.GameObserver;
import in4rows.game.ObservableGame;
import in4rows.player.Player;
import in4rows.player.PlayerType;

public interface IController {
	
	public ObservableGame openGame(Player p1,  Player p2, List<GameObserver> l) throws GameNotProperlyInitializedException;
	
	public ObservableGame openGame(Player p1, List<GameObserver> l) throws GameNotProperlyInitializedException;
	
	public Player createPlayer(PlayerType t, String id) throws ExistingPlayerException;

}
