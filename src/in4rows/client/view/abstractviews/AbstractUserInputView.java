package in4rows.client.view.abstractviews;

import in4rows.client.view.composite.IViewInput;


public abstract class AbstractUserInputView implements IViewInput {

	protected String choice;

	@Override
	public abstract void display();

	@Override
	public void setChoice(String choice) {
		this.choice = choice;
	}
}
