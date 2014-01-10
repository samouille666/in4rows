package in4rows.client.console.actions;

import in4rows.IController;

public class Screen1Action implements Action {

	IController controller;

	public Screen1Action(IController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void performAction(String input) {
		controller.setScreen1UserInput(input);
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

}
