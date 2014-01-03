package in4rows.model;

import in4rows.In4RowsFactory;
import in4rows.game.GameEvent;
import in4rows.player.AbstractPlayer;
import in4rows.player.EventDispatcher;
import in4rows.player.HumanPlayer;
import in4rows.player.ServerPlayer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestPlayer {

	In4RowsFactory f;

	@Before
	public void setup() {
		f = new In4RowsFactory();

	}

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
	@Ignore
	public void testPlayer_03() {
		EventDispatcher d = f.createEventDispatcher();
		
		DummyComputerPlayer p1 = new DummyComputerPlayer("toto");

		GameWritable g = f.createGame(p1);

		ServerPlayer p2 = f.createServerPlayer();
		
		g.setPlayer2(p2);

		Assert.assertEquals("not same game!", g, p1.getLastGame());
		Assert.assertTrue("not event start",
				GameEvent.Type.START.equals(p1.getLastEvent().getType()));
	}

}
