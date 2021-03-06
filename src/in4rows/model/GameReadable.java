package in4rows.model;

import in4rows.player.PlayerInGame;



public interface GameReadable {
	
	public String getId();
	
	public int getWidth();
	
	public int getHeight();
	
	public Disk getDisk(int row, int col);
	
	public Disk[][] getState();
	
	public boolean isDraw();
	
	public boolean isWon();
	
	public boolean isStopped();
	
	public PlayerInGame getP1();
	
	public PlayerInGame getP2();
	
	public PlayerInGame playerToPlay();

	public PlayerInGame playerNotToPlay();
	
	public Disk colorToPlay();
}
