package in4rows.client.console.actions;

import in4rows.client.IClientController;

public class Screen1Action implements Action {

	IClientController controller;

	public Screen1Action(IClientController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void performAction(String input) {
		controller.setScreen1UserInput(input);
	}

	public void setController(IClientController controller) {
		this.controller = controller;
	}

}
