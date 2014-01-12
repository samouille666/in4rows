package in4rows.client.console.views;

import in4rows.client.view.abstractviews.AbstractTextView;

public class TextView extends AbstractTextView {

	public TextView(String info) {
		super(info);
	}

	@Override
	public void display() {
		System.out.println(getInstruction());
	}

}
