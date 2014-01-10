package in4rows.client.view.abstractviews;

import in4rows.client.view.composite.IViewInstruction;


public abstract class InfoView implements IViewInstruction {

	private String info;

	public InfoView(String info) {
		super();
		this.info = info;
	}

	@Override
	public abstract void display();

	@Override
	public String getInstruction() {
		return info;
	}

}
