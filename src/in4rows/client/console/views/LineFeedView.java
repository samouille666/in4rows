package in4rows.client.console.views;

import in4rows.client.view.abstractviews.InfoView;

public class LineFeedView extends InfoView {

	public LineFeedView(String info) {
		super("");
	}

	@Override
	public void display() {
		System.out.println();
	}

}
