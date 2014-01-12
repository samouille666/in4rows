package in4rows.test;

import static in4rows.helper.GridHelper.countDiagLeft;
import static in4rows.helper.GridHelper.countDiagRight;
import static in4rows.helper.GridHelper.countDown;
import static in4rows.helper.GridHelper.countLeft;
import static in4rows.helper.GridHelper.countRight;
import static in4rows.helper.GridHelper.countUp;
import static in4rows.helper.GridHelper.firstDiskInColFromUp;
import static in4rows.helper.GridHelper.firstInCol_ModeCol;
import static in4rows.helper.GridHelper.firstInGame_ModeCol;
import in4rows.In4RowsServerFactory;
import in4rows.PlayerEventFactory;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.game.BasicGame;
import in4rows.helper.GridHelper;
import in4rows.model.BasicVertex;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.GameWritable;
import in4rows.model.Vertex;
import in4rows.player.BasicPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerInGame;
import in4rows.player.PlayerTurn;
import in4rows.player.PlayerType;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGridHelper {
	Player p1;
	Player p2;
	In4RowsServerFactory f;

	@Before
	public void setup() {
		p1 = new BasicPlayer("p1", PlayerType.HUMAN);
		p2 = new BasicPlayer("p2", PlayerType.MACHINE);

		f = new In4RowsServerFactory();
		f.setPlayerEventFactory(new PlayerEventFactory());
	}

	@Test
	public void testGridHelper_01() {
		DummyGrid d = new DummyGrid(10, 20);

		Assert.assertEquals("height not ok", 10, d.getHeight());
		Assert.assertEquals("width not ok", 20, d.getWidth());

		Assert.assertEquals("not v(0,0) !", new BasicVertex(0, 0),
				firstInCol_ModeCol(d, 0));

		d.setDisk(0, 0, Disk.BLACK);
		Assert.assertEquals("not v(0,0) !", Disk.BLACK, d.getDisk(0, 0));
		Assert.assertEquals("not v(0,0) !", new BasicVertex(1, 0),
				firstInCol_ModeCol(d, 0));

		d.setDisk(0, 5, Disk.BLACK);
		Assert.assertEquals("not v(5,5) !", Disk.BLACK, d.getDisk(0, 5));
		Assert.assertEquals("not v(5,5) !", new BasicVertex(1, 5),
				firstInCol_ModeCol(d, 5));
	}

	@Test
	public void testGridHelper_02() {
		DummyGrid d = new DummyGrid(3, 3);
		d.setDisk(0, 0, Disk.BLACK);
		d.setDisk(1, 0, Disk.BLACK);
		d.setDisk(2, 0, Disk.BLACK);

		Assert.assertEquals("not v()", new BasicVertex(0, 1),
				firstInGame_ModeCol(d));

	}

	@Test
	public void testGridHelper_03() {
		DummyGrid d = new DummyGrid(3, 3);
		d.setDisk(0, 0, Disk.BLACK);
		d.setDisk(1, 0, Disk.BLACK);
		d.setDisk(2, 0, Disk.BLACK);
		d.setDisk(0, 2, Disk.BLACK);

		List<Vertex> list = new ArrayList<>(2);
		list.add(new BasicVertex(0, 1));
		list.add(new BasicVertex(1, 2));
		Assert.assertEquals("not v()", list, GridHelper.possibleSquares(d));

	}

	@Test
	public void testGridHelper_04() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(7, 3, Disk.BLACK);
		d.setDisk(8, 3, Disk.BLACK);
		d.setDisk(5, 3, Disk.BLACK);

		Vertex v = new BasicVertex(6, 3);
		int countVertical = 1 + countUp(d, v) + countDown(d, v);
		Assert.assertEquals("not 4", 4, countVertical);

		d.setDisk(8, 3, Disk.WHITE);
		countVertical = 1 + countUp(d, v) + countDown(d, v);
		Assert.assertEquals("not 3", 3, countVertical);
	}

	@Test
	public void testGridHelper_05() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(6, 4, Disk.BLACK);
		d.setDisk(6, 5, Disk.BLACK);
		d.setDisk(6, 2, Disk.BLACK);

		Vertex v = new BasicVertex(6, 3);
		int countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 4 !! ", 4, countVertical);

		d.setDisk(6, 5, Disk.WHITE);
		countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 3 !! ", 3, countVertical);
	}

	@Test
	public void testGridHelper_06() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(6, 4, Disk.BLACK);
		d.setDisk(6, 5, Disk.BLACK);
		d.setDisk(6, 2, Disk.BLACK);

		Vertex v = new BasicVertex(6, 3);
		int countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 4 !! ", 4, countVertical);

		d.setDisk(6, 5, Disk.WHITE);
		countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 3 !! ", 3, countVertical);
	}

	@Test
	public void testGridHelper_07() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(4, 1, Disk.BLACK);
		d.setDisk(5, 2, Disk.BLACK);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(3, 0, Disk.BLACK);

		Vertex v = new BasicVertex(4, 1);
		int countVertical = 1 + countDiagRight(d, v) + countDiagLeft(d, v);
		Assert.assertEquals("not 4 !! ", 4, countVertical);

		d.setDisk(3, 0, Disk.WHITE);
		countVertical = 1 + countDiagRight(d, v) + countDiagLeft(d, v);
		Assert.assertEquals("not 3 !! ", 3, countVertical);
	}

	@Test
	public void testGridHelper_08() throws ErroneousPlayerEventException {

		GameWritable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);
		g.setPlayer2(p2);

		PlayerEvent e = f.createPlayerMoveEvent(g, p1, f.createMove(0),
				"one move");
		g.play(e);
		Assert.assertEquals("not v(0,0) !", new BasicVertex(0, 0),
				firstDiskInColFromUp(g, 0));

		e = f.createPlayerMoveEvent(g, p2, f.createMove(0), "one move");
		g.play(e);

		Assert.assertEquals("not v(1,0) !", new BasicVertex(1, 0),
				firstDiskInColFromUp(g, 0));

		e = f.createPlayerMoveEvent(g, p1, f.createMove(0), "one move");
		g.play(e);
		Assert.assertEquals("not v(2,0) !", new BasicVertex(2, 0),
				firstDiskInColFromUp(g, 0));
	}

	private class DummyGrid implements GameReadable {

		private Disk[][] grid;

		public DummyGrid(int row, int col) {
			grid = new Disk[row][col];
		}

		@Override
		public Disk getDisk(int row, int col) {
			return grid[row][col];
		}

		@Override
		public int getHeight() {
			return grid.length;
		}

		@Override
		public int getWidth() {
			return grid[0].length;
		}

		public void setDisk(int row, int col, Disk d) {
			grid[row][col] = d;
		}

		@Override
		public Disk[][] getState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isDraw() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isStopped() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isWon() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public PlayerInGame getP1() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PlayerInGame getP2() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
