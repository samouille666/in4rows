package in4rows.client.console.views;

import in4rows.client.view.abstractviews.AbstractTextView;

public class LineFeedView extends AbstractTextView {

	public LineFeedView(String info) {
		super("");
	}

	@Override
	public void display() {
		System.out.println();
	}

}
