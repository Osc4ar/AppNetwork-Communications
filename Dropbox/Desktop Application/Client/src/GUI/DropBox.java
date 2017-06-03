package GUI;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DropBox extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static JLabel l;
	
	public DropBox ( String nickname ) {
		
		//this.nickname = nickname;
		l = new JLabel ( "Welcome back: " + nickname ); 
		
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
		
		// Labels.
		l.setFont ( new Font ( "Helvetica", Font.BOLD, 40 ) );
		l.setBounds ( 10, 10, 600, 100 );
		
		// Add to frame.
		add ( l );
		
	} // End Components.

	public void actionPerformed(ActionEvent e) {
		
	} // End ActionListener.
	
} // End class.
