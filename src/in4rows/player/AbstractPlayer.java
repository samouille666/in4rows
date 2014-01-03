package in4rows.player;

import in4rows.game.GameEvent;
import in4rows.game.ObservableGame;
import in4rows.strategy.GameStrategy;

import java.util.Observable;

public abstract class AbstractPlayer implements ServerPlayer {

	private Dispatcher evtDispatch;

	protected GameStrategy s;

	private String id = null;

	public AbstractPlayer(String id) {
		this.id = id.toString();
	}

	public void setStrategy(GameStrategy s) {
		this.s = s;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setEventDispatcher(Dispatcher d) {
		evtDispatch = d;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (!(arg0 instanceof ObservableGame) & !(arg1 instanceof GameEvent))
			return;
		evtDispatch.executeEvent(new EventWorker(this, ((ObservableGame) arg0)
				.getGame(), (GameEvent) arg1));
		//update(((ObservableGame) arg0).getGame(), (GameEvent) arg1);
	}
}
