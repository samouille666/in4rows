package in4rows.client.graphical.disk;

import in4rows.client.graphical.decorator.IGraphicalComponent;

import java.io.PrintStream;

public class DiskO implements IGraphicalComponent {

	private PrintStream out = System.out;

	@Override
	public void setOutStream(PrintStream out) {
		this.out = out;
	}

	@Override
	public void draw() {
		out.print("O");
	}

	@Override
	public int getWidth() {
		return 1;
	}

}
