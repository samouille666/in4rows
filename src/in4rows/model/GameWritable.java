package in4rows.model;

import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.player.Player;
import in4rows.player.PlayerTurn;

public interface GameWritable extends GameReadable {

	public void setPlayer1(Player p1, Disk d, PlayerTurn t);

	public void setPlayer2(Player p2);
	
	public void play(PlayerEvent evt) throws ErroneousPlayerEventException;
	
	public GameEvent start() throws GameNotProperlyInitializedException;

}
