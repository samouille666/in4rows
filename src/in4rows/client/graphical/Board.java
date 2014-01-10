package in4rows.client.graphical;

import in4rows.client.graphical.border.horiz.HorizontalBorder2;
import in4rows.client.graphical.border.vert.VerticalBorder;
import in4rows.client.graphical.border.vert.VerticalBorder1;
import in4rows.client.graphical.decorator.EmptyComponent;
import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.client.graphical.disk.DiskO;
import in4rows.client.graphical.disk.DiskX;
import in4rows.client.graphical.separator.Separator;
import in4rows.client.graphical.separator.Separator1;
import in4rows.model.Disk;

public class Board implements IGraphicalComponent {

	private Disk[][] grid;

	private IGraphicalComponent diskO = new DiskO();
	private IGraphicalComponent diskX = new DiskX();
	private IGraphicalComponent side = new VerticalBorder1(new VerticalBorder(
			new EmptyComponent()));
	private IGraphicalComponent separator = new Separator1(new Separator());
	private IGraphicalComponent upside = new HorizontalBorder2();

	public Board(Disk[][] grid) {
		super();
		this.grid = grid;
	}

	@Override
	public void draw() {
		drawUpBorder();
		for (int i = 0; i < grid.length; i++)
			drawMiddleLine(i);
		drawDownBorder();
	}

	@Override
	public int getWidth() {
		return diskO.getWidth() * grid[0].length + side.getWidth() * 2
				+ separator.getWidth() * (grid[0].length - 1);
	}

	private void drawMiddleLine(int line) {
		side.draw();
		for (int i = 0; i < grid[0].length - 1; i++) {
			disk(grid[line][i]).draw();
			separator.draw();
		}
		disk(grid[line][grid[0].length - 1]).draw();
		side.draw();
		System.out.println();
	}

	private IGraphicalComponent disk(Disk d) {
		return Disk.BLACK.equals(d) ? diskO : diskX;
	}

	private void drawUpBorder() {
		drawBorder();
	}

	private void drawDownBorder() {
		drawBorder();
	}

	private void drawBorder() {
		int width = getWidth();
		for (int i = 0; i < width; i++) {
			upside.draw();
		}
		System.out.println();
	}

	public void setGrid(Disk[][] grid) {
		this.grid = grid;
	}

	public void setDiskO(IGraphicalComponent diskO) {
		this.diskO = diskO;
	}

	public void setDiskX(IGraphicalComponent diskX) {
		this.diskX = diskX;
	}

	public void setSide(IGraphicalComponent side) {
		this.side = side;
	}

	public void setUpside(IGraphicalComponent upside) {
		this.upside = upside;
	}
}
