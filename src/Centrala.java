import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class Centrala extends UnicastRemoteObject implements ICentrala,Serializable{
protected Centrala() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
List<IBoard> boardList = new ArrayList<>();
Registry register;
	public int register(IBoard s) throws RemoteException {
		try {
			boardList.add(s);
			return boardList.size()-1;
		} catch(Exception e) {
			return -1;
		}
	}

	public int unregister(int id) throws RemoteException {
		try {
			boardList.remove(id);
			return id;
		} catch(Exception e) {
			return -1;
		}
	}
	public static void main(String args[]) {
		try
		{
			Centrala centrala = new Centrala();
			Registry registry = LocateRegistry.createRegistry(1088);
			registry.rebind("ICentrala", centrala);
			
			System.out.println("Centrala enabled!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		while(true){}
	}
}
