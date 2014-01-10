package in4rows.test;

import in4rows.event.BasicPlayerEvent;
import in4rows.event.PlayerEvent;
import in4rows.event.PlayerEvent.Type;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.game.BasicGame;
import in4rows.model.BasicMove;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.GameWritable;
import in4rows.player.BasicPlayer;
import in4rows.player.Player;
import in4rows.player.PlayerTurn;
import in4rows.player.PlayerType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestGame {

	Player p1;
	Player p2;

	@Before
	public void setup() {
		p1 = new BasicPlayer("p1", PlayerType.HUMAN);
		p2 = new BasicPlayer("p2", PlayerType.MACHINE);
	}

	@Test
	public void testBasicGame_01() {
		GameReadable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);

		Assert.assertEquals("height not the same", 5, g.getHeight());
		Assert.assertEquals("width not the same", 7, g.getWidth());
		Assert.assertArrayEquals("state not the same", new Disk[5][7],
				g.getState());
	}

	@Test(expected = ErroneousPlayerEventException.class)
	public void testBasicGame_03() throws ErroneousPlayerEventException {
		GameWritable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);
		PlayerEvent e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0),
				"one move", p1);
		g.play(e);
	}

	@Test(expected = ErroneousPlayerEventException.class)
	public void testBasicGame_04() throws ErroneousPlayerEventException {

		GameWritable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);
		g.setPlayer2(p2);

		Player outsider = new BasicPlayer("outsider", PlayerType.HUMAN);

		PlayerEvent e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0),
				"one move", outsider);
		g.play(e);
	}

	@Test(expected = ErroneousPlayerEventException.class)
	public void testBasicGame_05() throws ErroneousPlayerEventException {

		GameWritable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);
		g.setPlayer2(p2);
		PlayerEvent e = new BasicPlayerEvent(Type.END, null, null, p2);
		g.play(e);
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p1);
		g.play(e);
	}

	@Test(expected = ErroneousPlayerEventException.class)
	public void testBasicGame_06() throws ErroneousPlayerEventException {

		GameWritable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);
		g.setPlayer2(p2);

		PlayerEvent e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0),
				"one move", p1);
		g.play(e);
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p2);
		g.play(e);
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p1);
		g.play(e);
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p2);
		g.play(e);
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p1);
		g.play(e);
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p2);
		g.play(e);
	}

	@Test(expected = ErroneousPlayerEventException.class)
	public void testBasicGame_07() throws ErroneousPlayerEventException {

		GameWritable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);
		g.setPlayer2(p2);

		PlayerEvent e = new BasicPlayerEvent(Type.MOVE, new BasicMove(7),
				"one move", p1);
		g.play(e);
	}

	@Test
	public void testBasicGame_08() throws ErroneousPlayerEventException {
		GameWritable g = new BasicGame(p1, Disk.BLACK, PlayerTurn.YES, 7, 5);
		g.setPlayer2(p2);

		PlayerEvent e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0),
				"one move", p1);
		g.play(e);
		Assert.assertFalse("not equal", g.isWon());
		Assert.assertFalse("not equal", g.isStopped());
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(1), "one move", p2);
		g.play(e);
		Assert.assertFalse("not equal", g.isWon());
		Assert.assertFalse("not equal", g.isStopped());
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p1);
		g.play(e);
		Assert.assertFalse("not equal", g.isWon());
		Assert.assertFalse("not equal", g.isStopped());
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(1), "one move", p2);
		g.play(e);
		Assert.assertFalse("not equal", g.isWon());
		Assert.assertFalse("not equal", g.isStopped());
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p1);
		g.play(e);
		Assert.assertFalse("not equal", g.isWon());
		Assert.assertFalse("not equal", g.isStopped());
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(1), "one move", p2);
		g.play(e);
		Assert.assertFalse("not equal", g.isWon());
		Assert.assertFalse("not equal", g.isStopped());
		e = new BasicPlayerEvent(Type.MOVE, new BasicMove(0), "one move", p1);
		g.play(e);

		Assert.assertTrue("not equal", g.isWon());
		Assert.assertTrue("not equal", g.isStopped());
	}
}
