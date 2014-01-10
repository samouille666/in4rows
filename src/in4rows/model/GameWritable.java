package in4rows.model;

import in4rows.event.ErroneousPlayerEventException;
import in4rows.event.PlayerEvent;

public interface GameWritable extends GameReadable {

	public void setPlayer1(Player p1, Disk d, PlayerTurn t);

	public void setPlayer2(Player p2);
	
	public void play(PlayerEvent evt) throws ErroneousPlayerEventException;

}
