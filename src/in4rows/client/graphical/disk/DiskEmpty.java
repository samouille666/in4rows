package in4rows.client.graphical.disk;

import in4rows.client.graphical.decorator.IGraphicalComponent;

public class DiskEmpty implements IGraphicalComponent {

	@Override
	public void draw() {
		System.out.print(" ");
	}
	
	@Override
	public int getWidth() {
		return 1;
	}

}
