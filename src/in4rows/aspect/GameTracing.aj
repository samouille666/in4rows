package in4rows.aspect;

import in4rows.client.graphical.board.Board;
import in4rows.event.GameEvent;
import in4rows.event.PlayerEvent;
import in4rows.exception.ErroneousPlayerEventException;
import in4rows.model.GameReadable;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public aspect GameTracing {

	/**
	 * one board will be used for all possible game logged that are running...
	 * */
	private Board board = new Board();

	/**
	 * Get the time difference between moves.
	 */
	private Map<String, Long> mTime = new HashMap<String, Long>();

	/**
	 * Cache of streaming resource, to avoid opening and closing is all the
	 * time.
	 */
	private Map<String, PrintStream> mStream = new HashMap<String, PrintStream>();

	/**
	 * @param gameid
	 *            the unique id of the game
	 * @return a print stream that will allow output log file writing
	 */
	private PrintStream getStream(String gameid) {

		PrintStream ps = this.mStream.get(gameid);
		if (ps != null)
			return ps;

		String filename = "log/server-game-" + gameid + ".log";

		try {
			ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(
					filename, true)));
			mStream.put(gameid, ps);
			return ps;
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	private Double getTimeErasedFromLastMove(String gameId) {
		Long startingGameTime = this.mTime.get(gameId);
		if (startingGameTime == null)
			this.mTime.put(gameId, System.currentTimeMillis());
		startingGameTime = this.mTime.get(gameId);

		// convert to second
		return new BigDecimal(System.currentTimeMillis() - startingGameTime)
				.divide(new BigDecimal(1000)).doubleValue();
	}

	/**
	 * The pointcut is define as the call of the interface of the Game that will
	 * hopefully cut at every possible implementation of this interface. Clause
	 * of class has been added not to call it many time.
	 */
	pointcut moveJustPlayed() : 
		within(in4rows.game.BasicObservableGame) 
		&& call(GameEvent in4rows.model.GameWritable.play(PlayerEvent) 
				throws ErroneousPlayerEventException);

	/**
	 * aim to provide place & time to erase the streaming resource
	 * 
	 * @param gr
	 *            a readable game
	 */
	pointcut gameTerminated(GameReadable gr) : 
		call(void in4rows.GameStopper.stop(GameReadable)) && args(gr);

	/**
	 * @param e
	 *            the game event (we implicitly choose the situation no
	 *            exception is returned in that case.
	 */
	after() returning(GameEvent e) : moveJustPlayed() {
		GameReadable gr = e.getGame();

		this.board.setGame(gr);
		PrintStream ps = getStream(gr.getId());
		this.board.setOutStream(ps);

		ps.println("Game : " + gr.getId());
		ps.format("Time erased : %.3f%n", getTimeErasedFromLastMove(gr.getId()));
		ps.println("Have played : " + gr.playerNotToPlay().getId());
		ps.println("To play : " + gr.playerToPlay().getId());
		this.board.draw();
	}

	/**
	 * close the stream object
	 * 
	 * @param gr
	 *            the game definitlely stopped on the server
	 */
	after(GameReadable gr) returning: gameTerminated(gr) {
		PrintStream ps = this.mStream.remove(gr.getId());
		if (ps != null)
			ps.close();
	}

}
