package in4rows.client.console;

import in4rows.client.IGameSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsoleClient {

	IGameSession session;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"in4rows/client/console/ConsoleClient-context.xml");
		// ApplicationContext ctx = new FileSystemXmlApplicationContext(
		// "conf/ConsoleClient-context.xml");
		ConsoleClient client = (ConsoleClient) ctx.getBean("consoleclient");
		client.startClientApp();

	}

	public void startClientApp() {
		session.startApp();
	}

	public void setGameSession(IGameSession gamesession) {
		this.session = gamesession;
	}

}
