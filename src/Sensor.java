import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;


public class Sensor extends UnicastRemoteObject implements Serializable,ISensor{
	protected Sensor() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
    SensorReadings sr;
	public SensorReadings getSensorReadings() throws RemoteException{
		Random r = new Random();
		sr.value = r.nextInt(30);
		return sr;
	}
}
