package in4rows.model;

import in4rows.In4RowsFactory;
import in4rows.player.ServerPlayer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestGame {

	private In4RowsFactory f;

	@Before
	public void setUp() {
		f = new In4RowsFactory();
	}

	@Test
	public void testGame_01() {

		ServerPlayer p1 = f.createServerPlayer();

		GameReadable g = f.createGame(20, 10, p1, Disk.BLACK, PlayerTurn.YES);

		Assert.assertTrue("w = 20", 20 == g.getWidth());
		Assert.assertTrue("h = 10", 10 == g.getHeight());

		for (int i = 0; i < g.getHeight(); i++) {
			for (int j = 0; j < g.getWidth(); j++) {
				Assert.assertTrue("not null !!!", null == g.getDisk(i, j));
			}
		}
	}

	@Test
	public void testGame_02() {

		ServerPlayer p1 = f.createServerPlayer();

		GameReadable g = f.createGame(p1);

		Assert.assertTrue("h = 10", 10 == g.getHeight());
		Assert.assertTrue("w = 10", 10 == g.getWidth());

		for (int i = 0; i < g.getHeight(); i++) {
			for (int j = 0; j < g.getWidth(); j++) {
				Assert.assertTrue("not null !!!", null == g.getDisk(i, j));
			}
		}
	}

	@Test
	public void testGame_03() {

		ServerPlayer p1 = f.createServerPlayer();

		GameWritable g = f.createGame(p1);

		Assert.assertTrue("h = 10", 10 == g.getHeight());
		Assert.assertTrue("w = 10", 10 == g.getWidth());

		for (int i = 0; i < g.getHeight(); i++) {
			for (int j = 0; j < g.getWidth(); j++) {
				Assert.assertTrue("not null !!!", null == g.getDisk(i, j));
			}
		}

		g.setDisk(5, Disk.BLACK);
		Assert.assertTrue("not BLACK !", Disk.BLACK.equals(g.getDisk(0, 5)));
	}

	@Test
	public void testTwoSimpleComputerGame() {
		
	}

}
