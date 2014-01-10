package in4rows.test;

import in4rows.event.BasicGameEvent;
import in4rows.event.GameEvent.Type;
import in4rows.model.BasicMove;
import in4rows.player.BasicPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestGameEvent {
	Player p1;
	Player p2;

	@Before
	public void setup() {
		p1 = new BasicPlayer("p1", PlayerType.HUMAN);
		p2 = new BasicPlayer("p2", PlayerType.MACHINE);
	}

	@Test
	public void testGameEvent() {
		BasicGameEvent bge = new BasicGameEvent(Type.START, new BasicMove(0),
				"start", p1, p2);
		BasicGameEvent expectedBge = new BasicGameEvent(Type.START,
				new BasicMove(0), "start", p1, p2);
		Assert.assertEquals("event not equals", expectedBge, bge);
	}
}
