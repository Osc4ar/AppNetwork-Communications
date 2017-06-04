package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class DropBox extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static JLabel lwelcome;
	private static JPanel panel1;
	private static JPanel panel;
	
	public DropBox ( String nickname ) {
		
		// Panels.
		panel1 = new JPanel ( new BorderLayout ( ) );
		panel = new JPanel ( new BorderLayout ( ) );
	
		// Labels.
		lwelcome = new JLabel ( "Welcome back: " + nickname ); 
		
	} // End constructor.
	
	public void Launch ( ) {
		
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		this.getContentPane ( ).setBackground ( Color.white );
		setLocationRelativeTo ( null );
		setTitle ( "Cloud Box" );
		setSize ( 800, 600 );
		setVisible ( true );
		setLayout ( null );
		Components ( );
		
	} // End Launch.
	
	public void Components ( ) {
		
		// Panels.
		panel.setBounds ( 10, 65, 200, 390 );
		panel1.setBounds ( 0, 0, 1300, 60 );
		panel1.setForeground ( Color.BLACK );
		panel1.add ( lwelcome );
		
		// Labels.
		lwelcome.setFont ( new Font ( "Helvetica", Font.BOLD, 18 ) );
		lwelcome.setBounds ( 40, 10, 600, 100 );
		lwelcome.setForeground ( Color.WHITE );
		
		// Add to frame.
		add ( panel1 );
		
	} // End Components.

	public void actionPerformed ( ActionEvent e ) {
		
	} // End ActionListener.
	
} // End class.
