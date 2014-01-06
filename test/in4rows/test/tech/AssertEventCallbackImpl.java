package in4rows.test.tech;

import in4rows.event.EventDispatcher;
import in4rows.event.GameEvent;
import in4rows.game.GameObserver;
import in4rows.model.GameReadable;

import java.util.Deque;

import org.junit.Assert;

public class AssertEventCallbackImpl implements GameObserver {

	private Deque<GameEvent> l;

	private EventDispatcher dispatcher;

	public AssertEventCallbackImpl() {
	}

	public AssertEventCallbackImpl(EventDispatcher dispatcher) {
		super();
		this.dispatcher = dispatcher;
	}

	public AssertEventCallbackImpl(Deque<GameEvent> l) {
		super();
		this.l = l;
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		GameEvent expected = l.poll();
		try {
			Assert.assertEquals("event is not the same.", expected.getType(),
					e.getType());
			Assert.assertEquals("event is not the same.",
					expected.getLastMove(), e.getLastMove());
			Assert.assertEquals("event is not the same.", expected.getMsg(),
					e.getMsg());
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(-1);
		}
	}

	public void setL(Deque<GameEvent> l) {
		this.l = l;
	}
}
