package in4rows.game;

import static in4rows.GridHelper.countDiagLeft;
import static in4rows.GridHelper.countDiagRight;
import static in4rows.GridHelper.countDown;
import static in4rows.GridHelper.countLeft;
import static in4rows.GridHelper.countRight;
import static in4rows.GridHelper.countUp;
import static in4rows.GridHelper.deepCopy;
import static in4rows.GridHelper.firstDiskInColFromUp;
import static in4rows.GridHelper.firstInCol_ModeCol;
import static in4rows.GridHelper.firstInGame_ModeCol;
import in4rows.event.ErroneousPlayerEventException;
import in4rows.event.PlayerEvent;
import in4rows.model.Disk;
import in4rows.model.GameRW;
import in4rows.model.GameReadable;
import in4rows.model.GameWritable;
import in4rows.model.Move;
import in4rows.model.Player;
import in4rows.model.PlayerTurn;
import in4rows.model.Vertex;
import in4rows.player.BasicPlayerInGame;
import in4rows.player.PlayerInGame;

public class BasicGame implements GameRW, GameReadable, GameWritable {
	private PlayerInGame p1;
	private PlayerInGame p2;
	private Disk[][] grid;

	// private PlayerEvent lastPlayerEvent;
	private boolean gameStopped = false;
	private boolean gameWon = false;

	public BasicGame(Player p1, Disk color, PlayerTurn t, int width, int height) {
		super();
		setPlayer1(p1, color, t);
		grid = new Disk[height][width];
	}

	@Override
	public Disk getDisk(int row, int col) {
		return grid[row][col];
	}

	private void setDisk(int col, Disk d) {
		Vertex v = firstInCol_ModeCol(this, col);
		grid[v.getRow()][col] = d;
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
	public void setPlayer1(Player p1, Disk d, PlayerTurn t) {
		this.p1 = new BasicPlayerInGame(p1, d, t);
	}

	@Override
	public void setPlayer2(Player p2) {
		if (p1 == null)
			return;
		this.p2 = new BasicPlayerInGame(p2,
				Disk.BLACK.equals(p1.getColor()) ? Disk.WHITE : Disk.BLACK,
				PlayerTurn.YES.equals(p1.getTurn()) ? PlayerTurn.NO
						: PlayerTurn.YES);
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

	@Override
	public Disk[][] getState() {
		return (Disk[][]) deepCopy(grid);
	}

	@Override
	public boolean isWon() {
		return gameWon;
	}

	private boolean isWon(Move last) {
		Vertex v = firstDiskInColFromUp(this, last.getCol());
		boolean vertical = 1 + countUp(this, v) + countDown(this, v) >= 4;
		boolean horizontal = 1 + countRight(this, v) + countLeft(this, v) >= 4;
		boolean diagonal = 1 + countDiagRight(this, v) + countDiagLeft(this, v) >= 4;
		if (vertical || horizontal || diagonal) {
			gameWon = true;
			return isWon();
		}
		return isWon();
	}

	@Override
	public boolean isDraw() {
		return firstInGame_ModeCol(this) == null;
	}

	@Override
	public PlayerInGame getP1() {
		return p1;
	}

	@Override
	public PlayerInGame getP2() {
		return p2;
	}

	private boolean checks(PlayerEvent evt)
			throws ErroneousPlayerEventException {
		if (p2 == null)
			throw new ErroneousPlayerEventException("No player 2 registered !");

		if (!evt.getPlayer().getId().equals(p1.getId())
				&& !evt.getPlayer().getId().equals(p2.getId()))
			throw new ErroneousPlayerEventException("Unknown player.");

		if (PlayerEvent.Type.END.equals(evt.getType())) {
			gameStopped = true;
			return false;
		}

		if (gameStopped)
			throw new ErroneousPlayerEventException("Game stopped.");

		if (!evt.getPlayer().getId().equals(playerToPlay().getId()))
			throw new ErroneousPlayerEventException("Not player "
					+ evt.getPlayer().getId() + " to play.");

		if (evt.getMove().getCol() < 0 || evt.getMove().getCol() >= getWidth())
			throw new ErroneousPlayerEventException("Erroneous move.");

		// move not possible notify error
		if (firstInCol_ModeCol(this, evt.getMove().getCol()) == null) {
			throw new ErroneousPlayerEventException("Erroneous move.");
		}

		return true;
	}

	@Override
	public boolean isStopped() {
		return gameStopped;
	}

	@Override
	public void play(PlayerEvent evt) throws ErroneousPlayerEventException {

		if (!checks(evt))
			return;

		// make it play
		Move last = evt.getMove();
		setDisk(last.getCol(), playerToPlay().getColor());
		// bgo.setChanged();

		if (isWon(last)) {
			gameStopped = true;
			// wonGame(playerToPlay());
			return;
		}

		if (isDraw()) {
			gameStopped = true;
			// drawGame(p1, p2, last);
			return;
		}

		exchangeTurn(p1, p2);
		// bgo.notifyObs(f
		// .createMoveEvent(playerToPlay(), playerNotToPlay(), last));
	}

	private static void exchangeTurn(PlayerInGame p1, PlayerInGame p2) {
		PlayerTurn p = p1.getTurn();
		p1.setTurn(p2.getTurn());
		p2.setTurn(p);
	}
}
