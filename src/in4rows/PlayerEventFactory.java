package in4rows;

import in4rows.event.BasicPlayerEvent;
import in4rows.event.PlayerEvent;
import in4rows.event.PlayerEvent.Type;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.Player;

public class PlayerEventFactory implements IPlayerEventFactory {

	@Override
	public PlayerEvent createPlayerEvent(Type type, String gameId,
			String playerId, Move m, String msg) {
		return new BasicPlayerEvent(type, m, msg, playerId, gameId);
	}

	@Override
	public PlayerEvent createPlayerEvent(Type type, String gameId,
			String playerId, Move m) {
		return createPlayerEvent(type, gameId, playerId, m, null);
	}

	@Override
	public PlayerEvent createPlayerEvent(Type type, GameReadable game,
			Player p, Move m, String msg) {
		return createPlayerEvent(type, game.getId(), p.getId(), m, msg);
	}

	@Override
	public PlayerEvent createPlayerEvent(Type type, GameReadable game,
			Player p, Move m) {
		return createPlayerEvent(type, game, p, m, null);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m, String msg) {
		return createPlayerEvent(Type.MOVE, gameId, playerId, m, msg);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m) {
		return createPlayerMoveEvent(gameId, playerId, m, null);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p,
			Move m, String msg) {
		return createPlayerEvent(Type.MOVE, game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p, Move m) {
		return createPlayerMoveEvent(game, p, m, null);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m, String msg) {
		return createPlayerEvent(Type.END, gameId, playerId, m, msg);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m) {
		return createPlayerEndEvent(gameId, playerId, m, null);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p,
			Move m, String msg) {
		return createPlayerEvent(Type.END, game, p, m, msg);
	}

	@Override
	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p, Move m) {
		return createPlayerEndEvent(game, p, m, null);
	}
}
