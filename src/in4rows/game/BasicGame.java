package in4rows.game;

import in4rows.In4RowsFactory;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.GameWritable;
import in4rows.model.Move;
import in4rows.model.Player;
import in4rows.model.PlayerTurn;
import in4rows.model.Vertex;
import in4rows.player.PlayerEvent;
import in4rows.player.PlayerInGame;
import in4rows.player.PlayerObserver;
import in4rows.player.ServerPlayer;
import static in4rows.GridHelper.*;

public class BasicGame implements GameReadable, GameWritable, PlayerObserver {

	// TODO inject by means of container if possible
	private In4RowsFactory f = new In4RowsFactory();

	private BasicObservableGame bgo;

	private PlayerInGame p1;

	private PlayerInGame p2;

	private Disk[][] grid;

	public BasicGame(int width, int height, ServerPlayer p1, Disk p1c,
			PlayerTurn p1t) {
		super();
		this.p1 = new PlayerInGame(p1);
		this.p1.setColor(p1c);
		this.p1.setTurn(p1t);
		grid = new Disk[height][width];

		p1.addObs(this);
		bgo = new BasicObservableGame(this);
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
		bgo.notifyObservers(f.createStartEvent(playerInTurn()));
	}

	private PlayerInGame playerInTurn() {
		return PlayerTurn.YES.equals(p1.getTurn()) ? p1 : p2;
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

	private void wonGame(Move last, PlayerInGame playerInTurn) {
		String msg = "The player " + playerInTurn.getId() + " has won the game.";
		bgo.notifyObservers(new BasicGameEvent(GameEvent.Type.WIN, last, msg, playerInTurn));		
	}

	private void drawGame(Player p, Move last) {
		String msg = "Player " + p1.getId() + " and player " + p2.getId() + " are draw";
		bgo.notifyObservers(new BasicGameEvent(GameEvent.Type.DRAW, last, msg, p));
	}

	@Override
	public void update(Player p, PlayerEvent e) {
		// 0) Check player's identity
		if (!p.getId().equals(p1) && !p.getId().equals(p2))
			return;
		// 1) update comes from player to play
		if (!p.getId().equals(playerInTurn().getId()))
			return;
		// 2) move not possible notify error
		if (firstInCol_ModeCol(this, e.getMove().getVertex().getCol()) == null) {
			bgo.notifyObs(new BasicGameEvent(
					GameEvent.Type.PRECEDING_MOVE_ERROR, null,
					"Impossible move !", p));
			return;
		}
		// make it play
		Move last = e.getMove();
		setDisk(last.getVertex().getCol(), playerInTurn().getColor());
		bgo.setChanged();
		
		// 3) has win ?
		if (hasWin(last)){
			wonGame(last, playerInTurn());
			return;
		}
		// 4) is it draw ?
		if (isDraw()){
			drawGame(p, last);
			return;
		}
		
		PlayerTurn.exchangeTurn(p1, p2);
		bgo.notifyObservers(new BasicGameEvent(GameEvent.Type.MOVE, last, p.getId() + " has moved.", p));
	}

}
