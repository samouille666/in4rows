package in4rows.client.graphical.separator;

import in4rows.client.graphical.decorator.IGraphicalComponent;

public class Separator implements IGraphicalComponent {
	@Override
	public void draw() {
		System.out.print("|");
	}

	@Override
	public int getWidth() {
		return 1;
	}
}
