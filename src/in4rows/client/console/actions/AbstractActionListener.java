package in4rows.client.console.actions;

import in4rows.client.IClientSession;

public abstract class AbstractActionListener<T> implements ActionListener<T> {

	protected IClientSession session;

	public AbstractActionListener(IClientSession session) {
		super();
		this.session = session;
	}
}
