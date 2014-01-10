package in4rows.client.console;

import in4rows.IController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ConsoleClient {

	IController controller;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"conf/ConsoleClient-context.xml");
		ConsoleClient client = (ConsoleClient) ctx.getBean("consoleclient");
		client.startClientApp();

	}

	public void startClientApp() {
		controller.startApp();
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

}
