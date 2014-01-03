package in4rows.model;

import in4rows.player.ServerPlayer;

public interface GameWritable extends GameReadable {
	
	public void setDisk(int col, Disk d);
	
	public void setPlayer2(ServerPlayer p2);

}
