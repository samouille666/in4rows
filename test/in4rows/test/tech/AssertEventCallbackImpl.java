package in4rows.test.tech;

import in4rows.event.GameEvent;
import in4rows.model.GameReadable;

import java.util.Deque;

import org.junit.Assert;

public class AssertEventCallbackImpl implements GameObserverCallBack {

	private Deque<GameEvent> l;

	public AssertEventCallbackImpl() {
	}

	public AssertEventCallbackImpl(Deque<GameEvent> l) {
		super();
		this.l = l;
	}

	@Override
	public void execute(GameReadable gr, GameEvent e) {
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
