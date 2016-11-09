package Authentiff;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import org.teamneko.schrodinger.rc522.MFRC522;

import javax.swing.ImageIcon;

public class authentiff extends Thread{

	private JFrame frame;
	private final int TIMER_SLEEP = 5000;
	private volatile boolean StopThread = false;
	public synchronized void Stop(){
		StopThread = true;
	}
	
	
	public String run(){ 					//type de fonction
			while(!StopThread){
				
				try {
					MFRC522 rc522=new MFRC522();
					if(rc522.ReadID())
					{
					
						return "/Authentiff/crochet_vert.png";
						Thread.sleep(TIMER_SLEEP);
						//Changer de Pan
					}
					else
					{
						return "/Authentiff/x_rouge.png";
					}
									
					
				} catch (UniformInterfaceException e) { //Exception
					e.printStackTrace();
				}
				return "";
				
				
	}
	}
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					authentiff window = new authentiff();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public String authentification(){
		MFRC522 rc522=new MFRC522();
		ImageIcon crochet = new ImageIcon("C:/Users/Felix Laframboise/workspace/InterfaceAuthentif/src/authentif/crochet_vert.png");
		ImageIcon rejet = new ImageIcon("C:/Users/Felix Laframboise/workspace/InterfaceAuthentif/src/authentif/x_rouge.png");
		
		try {
			if(rc522.ReadID())
			{
				return "/Authentiff/crochet_vert.png";
			}
			else
			{
				return "/Authentiff/x_rouge.png";
			}
		} catch (java.lang.InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public authentiff() {
		initialize();
		/*MFRC522 rc522=new MFRC522();
		ImageIcon crochet = new ImageIcon("C:/Users/Felix Laframboise/workspace/InterfaceAuthentif/src/authentif/crochet_vert.png");
		ImageIcon rejet = new ImageIcon("C:/Users/Felix Laframboise/workspace/InterfaceAuthentif/src/authentif/x_rouge.png");
		
		try {
			if(rc522.ReadID())
			{
							
			}
			else
			{
				
			}
		} catch (java.lang.InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblVeuillerScannerLa = new JLabel("Veuiller scanner la carte");
		lblVeuillerScannerLa.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblVeuillerScannerLa, BorderLayout.CENTER);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(authentiff.class.getResource(authentification()))); // changer call fonction?
		frame.getContentPane().add(label, BorderLayout.WEST);
	}

}
