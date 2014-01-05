package in4rows.game;

import static in4rows.GridHelper.countDiagLeft;
import static in4rows.GridHelper.countDiagRight;
import static in4rows.GridHelper.countDown;
import static in4rows.GridHelper.countLeft;
import static in4rows.GridHelper.countRight;
import static in4rows.GridHelper.countUp;
import static in4rows.GridHelper.firstInCol_ModeCol;
import static in4rows.GridHelper.firstInGame_ModeCol;

import org.springframework.beans.factory.annotation.Autowired;

import in4rows.In4RowsFactory;
import in4rows.event.PlayerEvent;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.GameWritable;
import in4rows.model.Move;
import in4rows.model.Player;
import in4rows.model.PlayerTurn;
import in4rows.model.Vertex;
import in4rows.player.PlayerInGame;
import in4rows.player.PlayerObserver;
import in4rows.player.ServerPlayer;

public class BasicGame implements GameReadable, GameWritable, PlayerObserver,
		ObservableGame {

	@Autowired
	private In4RowsFactory f = new In4RowsFactory();

	private BasicObservableGame bgo;

	private PlayerInGame p1;

	private PlayerInGame p2;

	private Disk[][] grid;

	public BasicGame(int width, int height, ServerPlayer p1, Disk p1c,
			PlayerTurn p1t, In4RowsFactory f) {
		super();

		this.f = f;

		this.p1 = new PlayerInGame(p1);
		this.p1.setColor(p1c);
		this.p1.setTurn(p1t);
		grid = new Disk[height][width];

		p1.addObs(this);
		bgo = new BasicObservableGame(this, f.createEventDispatcher());
		bgo.attachObs(p1);
	}

	@Override
	public int getWidth() {
		return grid[0].length;
	}

	@Override
	public int getHeight() {
		return grid.length;
	}

	@Override
	public Disk getDisk(int row, int col) {
		return grid[row][col];
	}

	@Override
	public void setDisk(int col, Disk d) {
		// TODO Application Exception in case of row/col not in the grid
		Vertex v = firstInCol_ModeCol(this, col);
		if (v != null) {
			grid[v.getRow()][col] = d;
		}
	}

	@Override
	public void setPlayer2(ServerPlayer p2) {
		// TODO start the game
		this.p2 = new PlayerInGame(p2);
		this.p2.setColor(Disk.BLACK.equals(p1.getColor()) ? Disk.WHITE
				: Disk.BLACK);
		this.p2.setTurn(PlayerTurn.YES.equals(p1.getTurn()) ? PlayerTurn.NO
				: PlayerTurn.YES);

		p2.addObs(this);
		bgo.attachObs(p2);
		bgo.setChanged();
		bgo.notifyObs(f.createStartEvent(playerToPlay(), playerNotToPlay()));
	}

	private PlayerInGame playerToPlay() {
		return PlayerTurn.YES.equals(p1.getTurn()) ? p1 : p2;
	}

	private PlayerInGame playerNotToPlay() {
		return PlayerTurn.NO.equals(p1.getTurn()) ? p1 : p2;
	}

	private PlayerInGame opponent(Player p) {
		return p.getId().equals(p1.getId()) ? p2 : p1;
	}

	private boolean hasWin(Move last) {
		Vertex v = last.getVertex();
		if (1 + countUp(this, v) + countDown(this, v) >= 4) {
			return true;
		} else if (1 + countRight(this, v) + countLeft(this, v) >= 4) {
			return true;
		} else if (1 + countDiagRight(this, v) + countDiagLeft(this, v) >= 4) {
			return true;
		}
		return false;
	}

	private boolean isDraw() {
		return firstInGame_ModeCol(this) == null;
	}

	private void wonGame(PlayerInGame playerInTurn) {
		bgo.notifyObs(f.createWinEvent(playerInTurn));
	}

	private void drawGame(Player p1, Player p2, Move last) {
		bgo.notifyObs(f.createDrawEvent(p1, p2, last));
	}

	@Override
	public void update(Player p, PlayerEvent e) {
		// 0) Check player's identity
		if (!p.getId().equals(p1.getId()) && !p.getId().equals(p2.getId()))
			return;
		// 1) update comes from player to play
		if (!p.getId().equals(playerToPlay().getId()))
			return;
		// 2) move not possible notify error
		if (firstInCol_ModeCol(this, e.getMove().getVertex().getCol()) == null) {
			bgo.notifyObs(f.createErrorEvent(p, opponent(p)));
			return;
		}
		// make it play
		Move last = e.getMove();
		setDisk(last.getVertex().getCol(), playerToPlay().getColor());
		bgo.setChanged();

		// 3) has win ?
		if (hasWin(last)) {
			wonGame(playerToPlay());
			return;
		}
		// 4) is it draw ?
		if (isDraw()) {
			drawGame(p1, p2, last);
			return;
		}

		PlayerTurn.exchangeTurn(p1, p2);
		bgo.notifyObs(f.createMoveEvent(playerToPlay(), playerNotToPlay(), last));
	}

	public void setF(In4RowsFactory f) {
		this.f = f;
	}

	@Override
	public void attachObs(GameObserver o) {
		bgo.attachObs(o);
	}

	@Override
	public void detachObs(GameObserver o) {
		bgo.detachObs(o);
	}

	@Override
	public GameReadable getGame() {
		return this;
	}
}
