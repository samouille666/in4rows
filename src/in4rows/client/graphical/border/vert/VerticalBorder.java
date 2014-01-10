package in4rows.client.graphical.border.vert;

import in4rows.client.graphical.decorator.AbstractGraphicalComponent;
import in4rows.client.graphical.decorator.IGraphicalComponent;

public class VerticalBorder extends AbstractGraphicalComponent implements IGraphicalComponent {
	
	public VerticalBorder(IGraphicalComponent c) {
		super(c);
	}

	@Override
	public void draw() {
		System.out.print("|");
	}

	@Override
	public int getWidth() {
		return 1;
	}
}
