package in4rows.client.console.views;

import in4rows.client.view.abstractviews.InfoView;

public class ChoicesView extends InfoView {

	public ChoicesView(String info) {
		super(info);
	}

	@Override
	public void display() {
		System.out.println(getInstruction());
	}

}
