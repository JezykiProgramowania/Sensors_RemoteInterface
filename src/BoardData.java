import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class BoardData extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float temperature; // t
	  public float wind; // w
	  public float precipation; // p
	
	  public float interval;
	  boolean toggle;
	  
	  public JPanel contentPane;
	  static ICentrala centrala;
	  static IBoard board;
	  Registry registry;
	 
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardData frame = new BoardData();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}
	public BoardData() throws RemoteException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board();
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
				boolean connected = false;
				
				while(!connected) {
					try {
						registry = LocateRegistry.getRegistry(1088);
						centrala = (ICentrala) registry.lookup("ICentrala");
						centrala.register(board);
						JOptionPane.showMessageDialog(new JPanel(), "Connected!");
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
