package in4rows.game;

import in4rows.event.Dispatchable;
import in4rows.event.EventDispatcher;
import in4rows.event.EventWorker;
import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.model.Disk;
import in4rows.model.GameRW;
import in4rows.player.Player;
import in4rows.player.PlayerInGame;
import in4rows.player.PlayerTurn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BasicObservableGame implements ObservableGame, Dispatchable,
		GameRW {

	private EventDispatcher dispatcher;
	// we just want the observer to be able to read the game
	private GameRW g;
	// not player observers
	private ArrayList<GameObserver> observers = new ArrayList<>();

	private boolean changed = false;

	public BasicObservableGame(GameRW g, EventDispatcher dispatcher) {
		this.g = g;
		this.dispatcher = dispatcher;
	}

	@Override
	public void setEventDispatcher(EventDispatcher d) {
		dispatcher = d;
	}

	@Override
	public Disk[][] getState() {
		return g.getState();
	}

	@Override
	public Disk getDisk(int row, int col) {
		return g.getDisk(row, col);
	}

	@Override
	public int getHeight() {
		return g.getHeight();
	}

	@Override
	public int getWidth() {
		return g.getWidth();
	}

	@Override
	public boolean isDraw() {
		return g.isDraw();
	}

	@Override
	public boolean isStopped() {
		return g.isStopped();
	}

	@Override
	public boolean isWon() {
		return g.isWon();
	}

	@Override
	public GameEvent play(PlayerEvent evt) throws ErroneousPlayerEventException {
		GameEvent e = g.play(evt);
		setChanged();
		notifyObs(e);
		if (GameEvent.Type.WIN.equals(e.getType())
				|| GameEvent.Type.DRAW.equals(e.getType()))
			finishGame();
		return e;
	}

	@Override
	public void setPlayer1(Player p1, Disk d, PlayerTurn t) {
		g.setPlayer1(p1, d, t);
	}

	@Override
	public void setPlayer2(Player p2) {
		g.setPlayer2(p2);
	}

	@Override
	public void attachObs(GameObserver o) {
		observers.add(o);
	}

	@Override
	public void detachObs(GameObserver o) {
		observers.remove(o);
	}

	public void notifyObs(GameEvent e) {
		if (!changed)
			return;

		List<Callable<Boolean>> l = new ArrayList<>();
		for (GameObserver o : observers)
			l.add(new EventWorker(o, g, e));
		dispatcher.executeUntilEnd(l);
		changed = false;
	}

	public void setChanged() {
		changed = true;
	}

	@Override
	public PlayerInGame getP1() {
		return g.getP1();
	}

	@Override
	public PlayerInGame getP2() {
		return g.getP2();
	}

	@Override
	public String getId() {
		return g.getId();
	}

	@Override
	public GameEvent start() throws GameNotProperlyInitializedException {
		GameEvent e = g.start();
		setChanged();
		notifyObs(e);
		return e;
	}

	@Override
	public GameEvent end() {
		GameEvent e = g.end();
		setChanged();
		notifyObs(e);
		finishGame();
		return e;
	}

	private void finishGame() {
		observers.clear();
	}

	@Override
	public PlayerInGame playerNotToPlay() {
		return g.playerNotToPlay();
	}

	@Override
	public PlayerInGame playerToPlay() {
		return g.playerToPlay();
	}

	@Override
	public Disk colorToPlay() {
		return g.colorToPlay();
	}

}
