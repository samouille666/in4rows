package in4rows.client.graphical.border.vert;

import in4rows.client.graphical.decorator.AbstractGraphicalComponent;
import in4rows.client.graphical.decorator.IGraphicalComponent;

public class VerticalBorder1 extends AbstractGraphicalComponent implements
		IGraphicalComponent {
	public VerticalBorder1(IGraphicalComponent c) {
		super(c);
	}

	@Override
	public void draw() {
		c.draw();
		System.out.print("|");
	}

	@Override
	public int getWidth() {
		return c.getWidth() + 1;
	}

}
