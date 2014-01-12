package in4rows.client.console.actions;

import in4rows.client.IGameSession;

public abstract class Action<T> {
	IGameSession controller;

	public Action(IGameSession controller) {
		super();
		this.controller = controller;
	}

	public void setController(IGameSession controller) {
		this.controller = controller;
	}

	public abstract void performAction(T intput);
}
