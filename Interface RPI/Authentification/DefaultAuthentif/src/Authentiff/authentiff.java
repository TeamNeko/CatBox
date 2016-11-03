package Authentiff;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import backend.catbox.teamneko.RC522.MFRC522;

public class authentiff {

	private JFrame frame;

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
	public authentiff() {
		initialize();
		MFRC522 rc522=new MFRC522();
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
		label.setIcon(new ImageIcon(authentiff.class.getResource("/Authentiff/crochet_vert.png")));
		frame.getContentPane().add(label, BorderLayout.WEST);
	}

}
