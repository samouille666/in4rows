package in4rows.client.graphical.separator;

import in4rows.client.graphical.decorator.AbstractGraphicalComponent;
import in4rows.client.graphical.decorator.IGraphicalComponent;

public class Separator1 extends AbstractGraphicalComponent implements
		IGraphicalComponent {
	public Separator1(IGraphicalComponent c) {
		super(c);
	}

	@Override
	public void draw() {
		System.out.print(" ");
		c.draw();
		System.out.print(" ");
	}

	@Override
	public int getWidth() {
		return c.getWidth() + 2;
	}

}
