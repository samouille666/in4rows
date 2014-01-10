package in4rows.client.graphical.decorator;


public class EmptyComponent implements IGraphicalComponent {

	@Override
	public void draw() {
		System.out.print("");
	}

	@Override
	public int getWidth() {
		return 0;
	}

}
