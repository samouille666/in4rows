package in4rows.client.graphical.border.horiz;

import in4rows.client.graphical.decorator.IGraphicalComponent;

public class HorizontalBorder3 implements IGraphicalComponent {
	@Override
	public void draw() {
		System.out.print("*");
	}

	@Override
	public int getWidth() {
		return 1;
	}
}
