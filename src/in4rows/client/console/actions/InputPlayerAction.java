package in4rows.client.console.actions;

import in4rows.client.IGameSession;

public class InputPlayerAction extends Action<String> {

	public InputPlayerAction(IGameSession controller) {
		super(controller);
	}

	@Override
	public void performAction(String input) {
		controller.setInputPlayer(input);
	}

}
