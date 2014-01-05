package in4rows.player;

import in4rows.event.GameEvent;
import in4rows.model.GameReadable;

public class HumanPlayer extends AbstractPlayer {

	public HumanPlayer(String id) {
		super(id);
	}

	@Override
	public void update(GameReadable gr, GameEvent e) {
		
	}	

	@Override
	public void addObs(PlayerObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delObs(PlayerObserver o) {
		// TODO Auto-generated method stub
		
	}
	
}
