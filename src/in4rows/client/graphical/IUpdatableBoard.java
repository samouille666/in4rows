package in4rows.client.graphical;

import in4rows.model.Disk;

public interface IUpdatableBoard {

	public void setGrid(Disk[][] grid);
	
	public Disk[][] getGrid();

}
