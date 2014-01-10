package in4rows.client.view.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeView implements IView {
	private List<IView> lView = new ArrayList<>();

	public void addView(IView v) {
		lView.add(v);
	}
	
	public void removeView(IView v){
		lView.remove(v);
	}

	@Override
	public void display() {
		for (IView v : lView) {
			v.display();
		}
	}
}
