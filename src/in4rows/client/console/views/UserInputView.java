package in4rows.client.console.views;

import in4rows.client.console.actions.Action;
import in4rows.client.view.abstractviews.AbstractUserInputView;

import java.util.Scanner;

public class UserInputView extends AbstractUserInputView {

	private boolean working = true;

	@Override
	@SuppressWarnings("resource")
	public void display() {
		if (!working)
			return;
		String sWhatever;
		Scanner in = new Scanner(System.in);
		sWhatever = in.nextLine();
		action.performAction(sWhatever);
	}

	private Action<String> action;

	@Override
	public void setWorking(boolean w_r) {
		working = w_r;
	}

	public void setAction(Action<String> action) {
		this.action = action;
	}
}
