package in4rows.model;

import in4rows.game.GameEvent;

import java.util.Deque;

import org.junit.Assert;

public class AssertEventCallbackImpl implements AssertEventCallBack {

	private Deque<GameEvent> l;
	
	@Override
	public void assertEvent(GameEvent e) {
		GameEvent expected = l.poll();
		Assert.assertEquals("event is not the same.", expected.getType(), e.getType());
		Assert.assertEquals("event is not the same.", expected.getMove().getVertex(), e.getMove().getVertex());
		Assert.assertEquals("event is not the same.", expected.getMsg(), e.getMsg());		
	}

	public void setL(Deque<GameEvent> l) {
		this.l = l;
	}
}
