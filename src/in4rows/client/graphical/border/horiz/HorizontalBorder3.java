package in4rows.client.graphical.border.horiz;

import in4rows.client.graphical.decorator.IGraphicalComponent;

import java.io.PrintStream;

public class HorizontalBorder3 implements IGraphicalComponent {

	private PrintStream out = System.out;

	@Override
	public void draw() {
		out.print("*");
	}

	@Override
	public void setOutStream(PrintStream out) {
		this.out = out;
	}

	@Override
	public int getWidth() {
		return 1;
	}
}
