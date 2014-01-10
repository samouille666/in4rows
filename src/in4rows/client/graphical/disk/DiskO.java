package in4rows.client.graphical.disk;

import in4rows.client.graphical.decorator.IGraphicalComponent;

public class DiskO implements IGraphicalComponent {

	@Override
	public void draw() {
		System.out.print("O");
	}
	
	@Override
	public int getWidth() {
		return 1;
	}

}
