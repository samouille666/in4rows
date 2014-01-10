package in4rows.test;

import in4rows.model.Disk;
import in4rows.model.PlayerTurn;
import in4rows.player.BasicPlayer;
import in4rows.player.BasicPlayerInGame;
import in4rows.player.PlayerInGame;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestPlayer {
	@Test
	public void testBasicPlayer() {
		Assert.assertEquals("id not equal to expected",
				new BasicPlayer("myid").getId(), "myid");
	}

	@Test
	public void testBasicPlayerInGame() {
		PlayerInGame actual = new BasicPlayerInGame(new BasicPlayer("myplayer"),
				Disk.BLACK, PlayerTurn.YES);
		PlayerInGame expected = new BasicPlayerInGame(new BasicPlayer("myplayer"),
				Disk.BLACK, PlayerTurn.YES);
		Assert.assertEquals("not equals ", expected, actual);
	}
}
