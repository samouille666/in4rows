package in4rows.client.console.actions;

import in4rows.client.IGameSession;

public class Screen1Action extends Action<String> {

	public Screen1Action(IGameSession controller) {
		super(controller);
	}

	@Override
	public void performAction(String input) {
		controller.setScreen1UserInput(input);
	}

}
