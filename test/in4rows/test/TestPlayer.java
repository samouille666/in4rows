package in4rows.test;

import in4rows.In4RowsFactory;
import in4rows.event.EventDispatcher;
import in4rows.event.GameEvent;
import in4rows.model.GameWritable;
import in4rows.model.Player;
import in4rows.player.AbstractPlayer;
import in4rows.player.HumanPlayer;
import in4rows.player.ServerPlayer;
import in4rows.test.tech.DummyComputerPlayer;
import in4rows.test.tech.DummyStrategy;
import in4rows.test.tech.NotExecuteCallback;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("TestPLayer-context.xml")
public class TestPlayer {

	@Autowired
	In4RowsFactory f;

	@Test
	public void testPlayer_01() {
		AbstractPlayer p = new HumanPlayer("toto");
		Assert.assertTrue("not equal", "toto".equals(p.getId()));
	}

	@Test
	public void testPlayer_02() {

		Player p1 = f.createPlayer();
		Player p2 = f.createPlayer();

		Assert.assertNotEquals("id equals :(", p1.getId(), p2.getId());
	}

	@Test
	public void testPlayer_03() {
		EventDispatcher d = f.createEventDispatcher();

		DummyComputerPlayer p1 = new DummyComputerPlayer("toto");
		p1.setEventCallback(new NotExecuteCallback());
		p1.setStrategy(new DummyStrategy());
		
		GameWritable g = f.createGame(p1);

		ServerPlayer p2 = f.createServerPlayer();

		g.setPlayer2(p2);

		Assert.assertEquals("not same game!", g, p1.getLastGame());
		Assert.assertTrue("not event start",
				GameEvent.Type.START.equals(p1.getLastEvent().getType()));
	}

}
