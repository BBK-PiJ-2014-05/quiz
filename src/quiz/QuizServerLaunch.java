package quiz;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class QuizServerLaunch implements Runnable{

public static void main(String[] args) {
		
	/** multi-threaded program run
		 * 
		 */
	
		QuizServerLaunch qsl = new QuizServerLaunch();
		Thread newThread = new Thread(qsl);
		newThread.start();
		qsl.launch();
		

	}
	
	public void launch(){
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			LocateRegistry.createRegistry(1099);
			
			QuizServer server = new QuizServer();
			String registryHost = "//localhost/";
			String serviceName = "quiz";
			Naming.rebind(registryHost + serviceName, server);
			
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

