import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class SensorReadings extends JFrame implements Serializable{
	public char category; // 't' - temperature, 'w' - wind, 'p' - precipitation
	  public float value; // wartoœæ, której jednostka zale¿y od kategorii
	  Registry registry;
	  IBoard board;
	  ISensor sensor;
	  public JPanel contentPane;
	  
	  public static void main(String args[]) {
		  EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SensorReadings frame = new SensorReadings();
						frame.setVisible(true);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	  }
	  public SensorReadings() throws RemoteException {
			
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			sensor = new Sensor();
			this.addWindowListener(new WindowListener() {
				
				public void windowOpened(WindowEvent e) {
					boolean connected = false;
					
					while(!connected) {
						try {
							System.out.println("thtrhtheegeg");
							registry = LocateRegistry.getRegistry();
							board = (IBoard) registry.lookup("IBoard");
							Scanner sc = new Scanner(System.in);
							System.out.println("Podaj typ sensora");
							char in = sc.next().charAt(0);
							board.register(sensor,'t');
							JOptionPane.showMessageDialog(new JPanel(), "Connected board with sensor of type " + in);
							connected = true;
						} catch (Exception ex) {
							System.out.println(ex.getMessage());
							
							int showConfirmDialog = JOptionPane.showConfirmDialog(
									new JPanel(), 
									"Cannot connect to the server...\nTry again?",
									"Customized Dialog", 
									JOptionPane.YES_NO_OPTION, 
									JOptionPane.INFORMATION_MESSAGE);
							
							if(showConfirmDialog==JOptionPane.NO_OPTION) {
								setVisible(false);
								System.exit(0);
							}
						}
					}
				}
				
				
				public void windowActivated(WindowEvent e) {}

				
				public void windowClosed(WindowEvent e) {}

				
				public void windowClosing(WindowEvent e) {}

				
				public void windowDeactivated(WindowEvent e) {}

				
				public void windowDeiconified(WindowEvent e) {}

				
				public void windowIconified(WindowEvent e) {}
			});
		}
}
