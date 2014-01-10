package in4rows.client.view.abstractviews;

import in4rows.client.view.composite.IViewInstruction;


public abstract class PlayAgainstView implements IViewInstruction {
	
	private String playAgainst = "Play against "; 	
	
	public PlayAgainstView(String addToInstruction) {
		playAgainst += addToInstruction; 
	}
	
	@Override
	public abstract void display();
	
	@Override
	public String getInstruction() {
		return playAgainst;
	}
		
}
