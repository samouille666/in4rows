package in4rows.client.console.actions;

import in4rows.client.IClientSession;

public abstract class Action<T> {
	IClientSession controller;

	protected ActionListener<T> actionListener;

	public Action(ActionListener<T> actionListener) {
		super();
		this.actionListener = actionListener;
	}

	public void setListener(ActionListener<T> actionListener) {
		this.actionListener = actionListener;
	}

	public abstract void performAction(T intput);
}
