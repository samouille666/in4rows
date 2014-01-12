package in4rows.test;

import in4rows.In4RowsServerFactory;
import in4rows.PlayerEventFactory;
import in4rows.event.PlayerEvent;
import in4rows.player.BasicPlayer;
import in4rows.player.PlayerType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestPlayerEvent {

	In4RowsServerFactory f;

	@Before
	public void setup() {
		f = new In4RowsServerFactory();
		f.setPlayerEventFactory(new PlayerEventFactory());
	}

	@Test
	public void testPlayerEvent() {

		PlayerEvent actual = f.createPlayerMoveEvent(null, new BasicPlayer(
				"anId", PlayerType.HUMAN).getId(), f.createMove(0), "this is a move");

		PlayerEvent expected = f.createPlayerMoveEvent(null, new BasicPlayer(
				"anId", PlayerType.HUMAN).getId(), f.createMove(0), "this is a move");

		Assert.assertEquals("not equal", expected, actual);
	}
}
