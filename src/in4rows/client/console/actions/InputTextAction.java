package in4rows.client.console.actions;

public class InputTextAction extends Action<String> {
	public InputTextAction(ActionListener<String> actionListener) {
		super(actionListener);
	}

	public void performAction(String intput) {
		actionListener.setInput(intput);
	};
}
