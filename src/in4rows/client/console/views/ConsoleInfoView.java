package in4rows.client.console.views;

import in4rows.client.view.abstractviews.InfoView;

public class ConsoleInfoView extends InfoView {

	public ConsoleInfoView(String info) {
		super(info);
	}

	@Override
	public void display() {
		System.out.println(getInstruction());
	}

}
