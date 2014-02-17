package in4rows.client.graphical.decorator;

import java.io.PrintStream;

public class AbstractGraphicalComponent implements IGraphicalComponent {

	protected IGraphicalComponent c;
	
	public AbstractGraphicalComponent(IGraphicalComponent c) {
		super();
		this.c = c;
	}

	@Override
	public void draw() {
		c.draw();
	}
	
	@Override
	public int getWidth() {
		return 0;
	}
	
	public void setGraphicalComponent(IGraphicalComponent g){
		c = g;
	}
	
	@Override
	public void setOutStream(PrintStream out) {
		c.setOutStream(out);
	}
}
