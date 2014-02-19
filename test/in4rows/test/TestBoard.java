package in4rows.test;

import in4rows.client.graphical.board.Board;
import in4rows.model.Disk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBoard {

	@Test
	public void testBoard_01() {
		Disk[][] grid = new Disk[7][5];
		fillTab(grid);
		Board b = new Board(grid);
		b.draw();
	}

	private void fillTab(Disk[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = Disk.BLACK;
			}
		}
	}

}
