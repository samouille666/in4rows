package in4rows;

import in4rows.event.PlayerEvent;
import in4rows.event.PlayerEvent.Type;
import in4rows.model.GameReadable;
import in4rows.model.Move;
import in4rows.player.Player;

public interface IPlayerEventFactory {

	public PlayerEvent createPlayerEvent(Type type, String gameId,
			String playerId, Move m, String msg);

	public PlayerEvent createPlayerEvent(Type type, String gameId,
			String playerId, Move m);

	public PlayerEvent createPlayerEvent(Type type, GameReadable game,
			Player p, Move m, String msg);

	public PlayerEvent createPlayerEvent(Type type, GameReadable game,
			Player p, Move m);

	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p,
			Move m, String msg);

	public PlayerEvent createPlayerMoveEvent(GameReadable game, Player p, Move m);

	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m, String msg);

	public PlayerEvent createPlayerMoveEvent(String gameId, String playerId,
			Move m);

	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p,
			Move m, String msg);

	public PlayerEvent createPlayerEndEvent(GameReadable game, Player p, Move m);

	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m, String msg);

	public PlayerEvent createPlayerEndEvent(String gameId, String playerId,
			Move m);

}
