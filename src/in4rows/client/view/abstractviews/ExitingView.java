package in4rows.client.view.abstractviews;

import in4rows.client.view.composite.IViewInstruction;


public abstract class ExitingView implements IViewInstruction {
	
	private String exitGame = "To exit the game, "; 	
	
	public ExitingView(String addToInstruction) {
		exitGame += addToInstruction; 
	}
	
	@Override
	public abstract void display();
	
	@Override
	public String getInstruction() {
		return exitGame;
	}
		
}
