package in4rows.client.view.abstractviews;

import in4rows.client.view.composite.IViewInstruction;


public abstract class AbstractTextView implements IViewInstruction {

	private String info;

	public AbstractTextView(String info) {
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
