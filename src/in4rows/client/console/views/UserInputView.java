package in4rows.client.console.views;

import in4rows.client.console.actions.Action;
import in4rows.client.view.abstractviews.AbstractUserInputView;

import java.util.Scanner;

public class UserInputView extends AbstractUserInputView {

	@Override
	@SuppressWarnings("resource")
	public void display() {
		String sWhatever;
		Scanner in = new Scanner(System.in);
		sWhatever = in.nextLine();
		action.performAction(sWhatever);
	}

	private Action<String> action;

	public void setAction(Action<String> action) {
		this.action = action;
	}
}
