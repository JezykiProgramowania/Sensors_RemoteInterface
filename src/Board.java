import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class Board extends UnicastRemoteObject implements Serializable,IBoard{
	  protected Board() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float temperature; // t
	  public float wind; // w
	  public float precipation; // p
	
	  public float interval;
	  boolean toggle;
	 
	  static ICentrala centrala;
	  static IBoard board;
	 // static BoardData board;
	  
	  List<ISensor> windSensors = new ArrayList<>();
	  List<ISensor> tempSensors = new ArrayList<>();
	  List<ISensor> precSensors = new ArrayList<>();
	public boolean register(ISensor s, char category) throws RemoteException {
		switch(category) {
		case 't': return tempSensors.add(s);
		case 'w': return windSensors.add(s);
		case 'p': return precSensors.add(s);
		default: {
			System.out.println("Unknown input");return false;
			}
		}
	}
	public boolean unregister(int index, char category) throws RemoteException {
		switch(category) {
		case 't': { tempSensors.remove(index); return true; }
		case 'w': { windSensors.remove(index); return true; }
		case 'p': { precSensors.remove(index); return true; }
		default: {
			System.out.println("Unknown input"); return false;
			}
		}
	}
	public void toggle() throws RemoteException {
		toggle = (toggle) ? false : true;
		
	}
	public void setUpdateInterval(long milisec) throws RemoteException {
		this.interval = milisec;
		
		
	}
	public static void main(String args[]) {
		try
		{
			Board board = new Board();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("IBoard", board);
			
			System.out.println("Board enabled!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}