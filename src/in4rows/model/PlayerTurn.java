package in4rows.model;

import in4rows.player.PlayerInGame;

/**
 * @author ssayag
 * 
 */
public enum PlayerTurn {

	YES, NO;

	public static void exchangeTurn(PlayerInGame p1, PlayerInGame p2) {
		PlayerTurn p = p1.getTurn();
		p1.setTurn(p2.getTurn());
		p2.setTurn(p);
	}
}
