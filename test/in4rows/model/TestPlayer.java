package in4rows.model;

import in4rows.GridHelper;
import in4rows.In4RowsFactory;
import in4rows.game.GameEvent;
import in4rows.player.AbstractPlayer;
import in4rows.player.HumanPlayer;
import in4rows.player.PlayerObserver;
import in4rows.player.ServerPlayer;
import in4rows.strategy.GameStrategy;

import org.junit.Assert;
import org.junit.Before;
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
	public void testPlayer_03() {
		DummyPlayer p1 = new DummyPlayer("toto");
		GameWritable g = f.createGame(p1);

		ServerPlayer p2 = f.createServerPlayer();
		g.setPlayer2(p2);

		Assert.assertEquals("not same game!", g, p1.getLastGame());
		Assert.assertTrue("not event start",
				GameEvent.Type.START.equals(p1.getLastEvent().getType()));
	}

	/**
	 * @author ssayag
	 * 
	 *         To test all the observer/update/event mechanism.
	 */
	private class DummyPlayer extends AbstractPlayer {

		private GameReadable game;

		private GameEvent evt;

		public DummyPlayer(String id) {
			super(id);
		}

		@Override
		public void update(GameReadable gr, GameEvent e) {
			game = gr;
			evt = e;
		}

		public GameEvent getLastEvent() {
			return evt;
		}

		public GameReadable getLastGame() {
			return game;
		}
		
		@Override
		public void addObs(PlayerObserver o) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void delObs(PlayerObserver o) {
			// TODO Auto-generated method stub
			
		}
	}

	private class DummyStrategy implements GameStrategy {

		private int col = 0;

		public DummyStrategy(int col) {
			this.col = col;
		}

		@Override
		public Move getMove(GameReadable g) {
			return new BasicMove(GridHelper.firstInCol_ModeCol(g, col));
		}
	}

}
