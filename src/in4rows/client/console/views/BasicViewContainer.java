package in4rows.client.console.views;

import in4rows.client.view.composite.CompositeView;
import in4rows.client.view.composite.IView;

public class BasicViewContainer extends CompositeView {

	public BasicViewContainer(IView v) {
		super();
		addView(v);
	}

	public BasicViewContainer() {
	}

}
