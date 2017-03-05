package GUI;

import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

import Client.Ticket;
import Product.Catalog;

public class Purchase extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	protected static DefaultListModel defaultList;
	protected static JList <Catalog> product;
	public static JButton previous;
	public static JButton confirm;
	public static JButton cancel;
	public static JButton remove;
	public static JButton next;
	private static int i;
	
	public Purchase ( ) {
		super ( );
		i = -1;
	} // End constructor.
	
	public void confirmPurchase ( ) {
		
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		setTitle ( "Confirm Purchase." );
		setLocationRelativeTo ( null );
		setSize ( 310, 220 );
		setVisible ( true );
		setLayout ( null );
		Components ( );
		
	} // End method.
	
	public void Components ( ) {
		
		/* Buttons. */
		previous = new JButton ( "Previous" );
		confirm = new JButton ( "Confirm" );
		cancel = new JButton ( "Cancel" );
		remove = new JButton ( "Remove" );
		next = new JButton ( "Next" );
		
		previous.setBounds ( 190, 140, 95, 20 );
		confirm.setBounds ( 190, 20, 95, 20 );
		cancel.setBounds ( 20, 140, 95, 20 );
		remove.setBounds ( 190, 100, 95, 20 );
		next.setBounds ( 190, 60, 95, 20 );
		add ( previous );
		add ( confirm );
		add ( cancel );
		add ( remove );
		add ( next );
		
		/* Lists. */
		defaultList = new DefaultListModel ( );
		product = new JList <Catalog> ( defaultList );
		product.setBounds ( 20, 20, 150, 100 );
		add( product );
		
		/* Buttons actions. */
		previous.addActionListener ( this );
		confirm.addActionListener ( this );
		cancel.addActionListener ( this );
		remove.addActionListener ( this );
		next.addActionListener ( this );
		
	} // End method.

	@Override
	public void actionPerformed ( ActionEvent e ) {
		
		if ( e.getSource ( ) == next && windowGUI.shopping.size ( ) != 0 ) {
			i++;
			if ( i == windowGUI.shopping.size ( ) ) {
				i = 0;
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + windowGUI.shopping.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + windowGUI.shopping.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + windowGUI.shopping.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + windowGUI.shopping.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + windowGUI.shopping.get ( i ).getOfert ( ) );
				//i++;
			} else {
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + windowGUI.shopping.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + windowGUI.shopping.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + windowGUI.shopping.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + windowGUI.shopping.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + windowGUI.shopping.get ( i ).getOfert ( ) );
				//i++;
			} // End if - else.
		} // End if.
		
		if ( e.getSource ( ) == previous && windowGUI.shopping.size ( ) != 0 ) {
			--i;
			if ( i < 0 ) {
				i = windowGUI.shopping.size ( ) - 1;
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + windowGUI.shopping.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + windowGUI.shopping.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + windowGUI.shopping.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + windowGUI.shopping.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + windowGUI.shopping.get ( i ).getOfert ( ) );
			} else {
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + windowGUI.shopping.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + windowGUI.shopping.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + windowGUI.shopping.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + windowGUI.shopping.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + windowGUI.shopping.get ( i ).getOfert ( ) );
			} // End if - else.
		} // End if.
		
		if ( e.getSource ( ) == remove && windowGUI.shopping.size ( ) != 0 ) {
			windowGUI.shopping.remove ( i );
		} // End if.
		
		if ( e.getSource ( ) == confirm ) {
			Ticket.ticket ( );
			dispose ( );
		} // End if.
		
		if ( e.getSource ( ) == cancel ) {
			dispose ( );
		} // End if.
	} // End method. 
	
} // End Ticket class.
