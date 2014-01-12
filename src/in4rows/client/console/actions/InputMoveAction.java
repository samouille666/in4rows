package in4rows.client.console.actions;

import in4rows.client.IGameSession;

public class InputMoveAction extends Action<Integer> {

	public InputMoveAction(IGameSession controller) {
		super(controller);
	}

	@Override
	public void performAction(Integer intput) {
		controller.setMove(intput);
	}
}
