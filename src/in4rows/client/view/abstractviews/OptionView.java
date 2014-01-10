package in4rows.client.view.abstractviews;

import in4rows.client.view.composite.IViewInstruction;


public abstract class OptionView implements IViewInstruction {
	
	private String option = "Go to option"; 	
	
	public OptionView(String addToInstruction) {
		option += addToInstruction; 
	}
	
	@Override
	public abstract void display();
	
	@Override
	public String getInstruction() {
		return option;
	}
		
}
