package in4rows.test;

import in4rows.In4RowsFactory;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.GameWritable;
import in4rows.model.PlayerTurn;
import in4rows.player.ServerPlayer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("TestGame-context.xml")
public class TestGame {

	@Autowired
	private In4RowsFactory f;

	@Test
	public void testGame_01() {

		ServerPlayer p1 = f.createServerPlayer();

		GameWritable g = f.createGame(20, 10, p1, Disk.BLACK, PlayerTurn.YES);

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


}
