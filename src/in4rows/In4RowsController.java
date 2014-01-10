package in4rows;

import in4rows.client.console.factory.ClientFactory;
import in4rows.client.view.composite.IView;

public class In4RowsController implements IController {

	private ClientFactory factory;

	private IView screen1 = null;
	private IView screen1Error = null;

	private String screen1UserInput;

	public In4RowsController(ClientFactory factory) {
		screen1 = factory.createStartingScreen(this);           
		screen1Error = factory.createStartingScreenError(this); 
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see in4rows.IController#getScreen1choice()
	 */
	@Override
	public String getScreen1UserInput() {
		return screen1UserInput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in4rows.IController#setScreen1choice(java.lang.String)
	 */
	@Override
	public void setScreen1UserInput(String screen1choice) {
		this.screen1UserInput = screen1choice;
	}

	private int userChoice1() {
		Integer res = null;
		try {
			res = Integer.parseInt(getScreen1UserInput());
		} catch (NumberFormatException e) {
			return 0;
		}
		return res;
	}

	@Override
	public void startApp() {
		screen1.display();
		int choice = userChoice1();
		while (choice < 1 || choice > 3) {
			screen1Error.display();
			choice = userChoice1();
		}
	}

	public void setFactory(ClientFactory factory) {
		this.factory = factory;
	}

}
