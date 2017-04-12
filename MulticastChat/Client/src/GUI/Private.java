package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.DatagramPacket;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

/* This class allows two clients to interact each other sending
 * private messages, the messages won't be sended to the common chat
 * instead, they will have a personal window.
 */

public class Private extends JFrame implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;
	protected static JScrollPane scroller;
	protected static String username;
	protected static JEditorPane ep;
	protected static JTextField tf;
	protected static String aux;
	public static Thread t;
	
	public Private ( ) {
		
		super ( "Private Chat." );
		this.getContentPane ( ).setBackground ( Color.WHITE );
		ep = new JEditorPane ( );
		tf = new JTextField ( );
		t = new Thread ( this );
		aux = "";
		scroller = new JScrollPane ( ep, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

	} // End constructor.
	
	public void Components ( String username ) {
		
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo ( null );
		setResizable ( false );
		setSize ( 300, 340 );
		setVisible ( true );
		setLayout ( null );
		Private.username = username;
		//t.start ( );
		Attributes ( );
		
	} // End Components. 
	
	public void Attributes ( ) {
		
		/* Attributes. */
		scroller.setBounds ( 20, 20, 260, 240 );
		tf.setBounds ( 20, 280, 260, 20 );
		
		tf.setForeground ( Color.RED );
		ep.setForeground ( Color.RED );
		
		tf.setFont ( new Font ( "Comic Sans MS", Font.BOLD, 12 ) );
		ep.setFont ( new Font ( "Comic Sans MS", Font.BOLD, 12 ) );
		ep.setEditable ( false );
		
		add ( scroller );
		add ( tf );
		
		/* Action Performed. */
		tf.addActionListener ( this );
		
	} // End Attributes.
	
	@Override
	public void actionPerformed ( ActionEvent e ) {
		
		if ( e.getSource ( ) == tf ) {
			String s = tf.getText ( );
			s = username + s;
			tf.setText ( "" );
		} // End if.
	} // End actionPerformed.

	@Override
	public void run ( ) {
		
		for ( ; ; ) {
			DatagramPacket p = new DatagramPacket ( new byte [ 1500 ], 1500 );
			String msg = new String ( p.getData ( ), 0, p.getLength ( ) );
			aux = aux + msg + "\n";
			ep.setText ( aux );
		} // End for.
		
	} // End Thread.

} // End class.
