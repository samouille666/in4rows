package in4rows.game;

import static in4rows.helper.GridHelper.countDiagLeft;
import static in4rows.helper.GridHelper.countDiagRight;
import static in4rows.helper.GridHelper.countDown;
import static in4rows.helper.GridHelper.countLeft;
import static in4rows.helper.GridHelper.countRight;
import static in4rows.helper.GridHelper.countUp;
import static in4rows.helper.GridHelper.deepCopy;
import static in4rows.helper.GridHelper.firstDiskInColFromUp;
import static in4rows.helper.GridHelper.firstInCol_ModeCol;
import static in4rows.helper.GridHelper.firstInGame_ModeCol;
import in4rows.In4RowsServerFactory;
import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.exception.GameNotProperlyInitializedException;
import in4rows.model.Disk;
import in4rows.model.GameRW;
import in4rows.model.GameReadable;
import in4rows.model.GameWritable;
import in4rows.model.Move;
import in4rows.model.Vertex;
import in4rows.player.BasicPlayerInGame;
import in4rows.player.Player;
import in4rows.player.PlayerInGame;
import in4rows.player.PlayerTurn;

import java.util.UUID;

public class BasicGame implements GameRW, GameReadable, GameWritable {
	private In4RowsServerFactory factory;

	private String id;

	private PlayerInGame p1;
	private PlayerInGame p2;
	private Disk[][] grid;

	// private PlayerEvent lastPlayerEvent;
	private boolean gameStarted = false;
	private boolean gameStopped = false;
	private boolean gameWon = false;

	public BasicGame(Player p1, Disk color, PlayerTurn t, int width, int height) {
		super();
		setPlayer1(p1, color, t);
		grid = new Disk[height][width];
		id = UUID.randomUUID().toString();
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

		if (!evt.getPlayerId().equals(p1.getId())
				&& !evt.getPlayerId().equals(p2.getId()))
			throw new ErroneousPlayerEventException("Unknown player.");

		if (PlayerEvent.Type.END.equals(evt.getType())) {
			gameStopped = true;
			return false;
		}

		if (gameStopped)
			throw new ErroneousPlayerEventException("Game stopped.");

		if (!evt.getPlayerId().equals(playerToPlay().getId()))
			throw new ErroneousPlayerEventException("Not player "
					+ evt.getPlayerId() + " to play.");

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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public GameEvent start() throws GameNotProperlyInitializedException {
		if (gameStarted)
			throw new GameNotProperlyInitializedException(
					"Game already started !");
		boolean sizeGame = getHeight() > 4 && getWidth() > 4;
		boolean enoughPlayers = p1 != null && p2 != null;
		if (!sizeGame || !enoughPlayers)
			throw new GameNotProperlyInitializedException(
					"Game not properly initialized !");
		return factory.createStartEvent(this, playerToPlay(),
				playerNotToPlay());
	}

	public void setFactory(In4RowsServerFactory factory) {
		this.factory = factory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicGame other = (BasicGame) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicGame [id=" + id + ", p1=" + p1 + ", p2=" + p2
				+ ", gameStarted=" + gameStarted + ", gameStopped="
				+ gameStopped + ", gameWon=" + gameWon + "]";
	}

}
