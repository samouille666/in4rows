package in4rows.client.console.actions;

import in4rows.client.IClientController;

public class InputPlayerAction implements Action {

	IClientController controller;

	public InputPlayerAction(IClientController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void performAction(String input) {
		controller.setInputPlayer(input);
	}

	public void setController(IClientController controller) {
		this.controller = controller;
	}

}
