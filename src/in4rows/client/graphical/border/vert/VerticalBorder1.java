package in4rows.client.graphical.border.vert;

import in4rows.client.graphical.decorator.AbstractGraphicalComponent;
import in4rows.client.graphical.decorator.IGraphicalComponent;

import java.io.PrintStream;

public class VerticalBorder1 extends AbstractGraphicalComponent implements
		IGraphicalComponent {

	private PrintStream out = System.out;

	public VerticalBorder1(IGraphicalComponent c) {
		super(c);
	}
	
	@Override
	public void setOutStream(PrintStream out) {
		super.c.setOutStream(out);
		this.out = out;
	}

	@Override
	public void draw() {
		c.draw();
		out.print("|");
	}

	@Override
	public int getWidth() {
		return c.getWidth() + 1;
	}

}
