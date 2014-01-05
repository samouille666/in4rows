package in4rows.test;

import in4rows.In4RowsFactory;
import in4rows.model.GameWritable;
import in4rows.test.tech.AssertEventCallbackBuilder;
import in4rows.test.tech.DummyComputerPlayer;
import in4rows.test.tech.DummyGameObserver;
import in4rows.test.tech.DummyStrategy;
import in4rows.test.tech.WaitForFinishedGameObserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("TestComputerGame-context.xml")
public class TestComputerGame {

	@Autowired
	private In4RowsFactory f;
	@Autowired
	private AssertEventCallbackBuilder callBackBuilder;
	@Autowired
	private DummyComputerPlayer p1;
	@Autowired
	private DummyComputerPlayer p2;
	@Autowired
	private DummyStrategy s1;
	@Autowired
	private DummyStrategy s2;
	@Autowired
	private DummyGameObserver dummyGo;
	@Autowired
	private WaitForFinishedGameObserver finishedCallback;

	@Test
	public void testTwoSimpleComputerGame() {
		int[] p1RowsMove = new int[] { 0, 1, 2, 3 };
		int[] p1ColsMove = new int[] { 0, 0, 0, 0 };
		int[] p2RowsMove = new int[] { 0, 1, 2 };
		int[] p2ColsMove = new int[] { 1, 1, 1 };

		// move list p1
		callBackBuilder.setBuildingElement(p1RowsMove, p1ColsMove, p1);
		p1.setEventCallback(callBackBuilder.getCallback());
		s1.setMoves(p1RowsMove, p1ColsMove);

		// move list p2
		callBackBuilder.setBuildingElement(p2RowsMove, p2ColsMove, p2);
		p1.setEventCallback(callBackBuilder.getCallback());
		s2.setMoves(p2RowsMove, p2ColsMove);

		GameWritable g = f.createGame(p1);
		dummyGo.setCallback(finishedCallback);
		g.attachObs(dummyGo);
		g.setPlayer2(p2);

		while (!finishedCallback.isGameFinished())
			;
	}

}
