package in4rows.client.console;

import in4rows.client.console.factory.ClientFactory;
import in4rows.exception.GameNotProperlyInitializedException;

public interface IMatch {

	public boolean isFinished();
	
	public boolean setFinished(boolean finished);

	public void display();

	public void init() throws GameNotProperlyInitializedException;
	
	public void play();
	
	public void setFactory(ClientFactory f);
}
