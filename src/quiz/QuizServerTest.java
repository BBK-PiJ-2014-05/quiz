package quiz;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

public class QuizServerTest{

	@Test
	public void testNewPlayer() throws RemoteException {
		QuizServer server = new QuizServer();
		int id = server.addNewPlayer("Dave");
		boolean output = server.checkIfPlayerExists(id);
		boolean expected = true;
		assertEquals(expected,output);
	}

}
