package in4rows.client.graphical.decorator;

import java.io.PrintStream;


public class EmptyComponent implements IGraphicalComponent {

	private PrintStream out = System.out;

	@Override
	public void setOutStream(PrintStream out) {
		this.out = out;
	}
	
	@Override
	public void draw() {
		out.print("");
	}

	@Override
	public int getWidth() {
		return 0;
	}

}
