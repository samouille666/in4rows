package in4rows.client.graphical.decorator;

import java.io.PrintStream;

public interface IGraphicalComponent {
	public void draw();
	public int getWidth();
	public void setOutStream(PrintStream out);
}
