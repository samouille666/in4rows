package in4rows.client.graphical.separator;

import in4rows.client.graphical.decorator.AbstractGraphicalComponent;
import in4rows.client.graphical.decorator.IGraphicalComponent;

import java.io.PrintStream;

public class Separator1 extends AbstractGraphicalComponent implements
		IGraphicalComponent {

	private PrintStream out = System.out;

	public Separator1(IGraphicalComponent c) {
		super(c);
	}

	@Override
	public void draw() {
		out.print(" ");
		c.draw();
		out.print(" ");
	}

	@Override
	public int getWidth() {
		return c.getWidth() + 2;
	}

	@Override
	public void setOutStream(PrintStream out) {
		super.c.setOutStream(out);
		this.out = out;
	}

}
