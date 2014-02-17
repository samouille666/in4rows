package in4rows.client.graphical;

import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.model.GameReadable;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoggableBoardProxy implements InvocationHandler {

	private Object obj;

	public static Object newInstance(Object obj) {
		return java.lang.reflect.Proxy.newProxyInstance(obj.getClass()
				.getClassLoader(), obj.getClass().getInterfaces(),
				new LoggableBoardProxy(obj));
	}

	private LoggableBoardProxy(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		Object result;
		try {
			result = m.invoke(obj, args);
			
			if ("setGame".equals(m.getName()))
				logInformations(proxy, (GameReadable) args[0]);
			
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: "
					+ e.getMessage());
		} finally {
			// System.out.println("after method " + m.getName());
		}
		return result;
	}

	private void logInformations(Object proxy, GameReadable g)
			throws FileNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		String filename = "game-" + g.getId() + ".log";

		Method setOutStream = IGraphicalComponent.class.getDeclaredMethod(
				"setOutStream", PrintStream.class);
		Method draw = IGraphicalComponent.class.getDeclaredMethod("draw");

		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream(filename, true)));

		setOutStream.invoke(obj, out);
		out.println("Game : " + g.getId());
		out.println("Player 1 " + g.getP1().getId());
		out.println("Player 1 " + g.getP2().getId());
		draw.invoke(obj);
		setOutStream.invoke(obj, System.out);
		out.close();
	}
}
