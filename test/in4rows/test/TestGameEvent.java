package in4rows.test;

import in4rows.event.BasicGameEvent;
import in4rows.event.GameEvent.Type;
import in4rows.model.BasicMove;
import in4rows.player.BasicPlayer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestGameEvent {

	@Test
	public void testGameEvent() {
		BasicGameEvent bge = new BasicGameEvent(Type.START, new BasicMove(0),
				"start", new BasicPlayer("p1"), new BasicPlayer("p2"));
		BasicGameEvent expectedBge = new BasicGameEvent(Type.START,
				new BasicMove(0), "start", new BasicPlayer("p1"),
				new BasicPlayer("p2"));
		Assert.assertEquals("event not equals", expectedBge, bge);
	}
}
