package in4rows.client.console.views;

import in4rows.client.console.actions.Action;
import in4rows.client.view.abstractviews.GetUserChoiceView;

import java.util.Scanner;

public class ConsoleInputNumberView extends GetUserChoiceView {

	@Override
	public void display() {
		String sWhatever;

		Scanner in = new Scanner(System.in);

	       // Reads a single line from the console 
	       // and stores into name variable
		sWhatever = in.nextLine();

		action.performAction(sWhatever);

	}

	private Action action;

	public void setAction(Action action) {
		this.action = action;
	}

}
