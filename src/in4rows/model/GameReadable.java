package in4rows.model;


public interface GameReadable {
	
	public int getWidth();
	
	public int getHeight();
	
	public Disk getDisk(int row, int col);

}
