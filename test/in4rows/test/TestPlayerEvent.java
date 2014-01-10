package in4rows.test;

import in4rows.event.BasicPlayerEvent;
import in4rows.event.PlayerEvent;
import in4rows.event.PlayerEvent.Type;
import in4rows.model.BasicMove;
import in4rows.player.BasicPlayer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestPlayerEvent {

	@Test
	public void testPlayerEvent() {
		PlayerEvent actual = new BasicPlayerEvent(Type.MOVE, new BasicMove(0),
				"this is a move", new BasicPlayer("anId"));
		PlayerEvent expected = new BasicPlayerEvent(Type.MOVE,
				new BasicMove(0), "this is a move", new BasicPlayer("anId"));
		Assert.assertEquals("not equal", expected, actual);
	}
}
