package GUI;

import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import Product.Catalog;
import java.awt.Image;
import java.awt.Font;
import Client.Client;

public class windowGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	protected static DefaultListModel defaultList;
	public static ArrayList <Catalog> shopping;
	protected static JLabel modelImage;
	protected static JButton shopCart;
	protected static JButton previous;
	protected JList <Catalog> product;
	protected static JLabel nameShop;
	protected static JButton toCart;
	protected static JButton next;
	protected int i;
	
	public windowGUI ( ) {
		
		super ( "D&H SHOP... BUY ON THE CLOUD." );
		nameShop = new JLabel ( "D & H Shop... Buy on the Cloud." );
		shopCart = new JButton ( "Shopping Cart" );
		toCart = new JButton ( "Add To Cart" );
		previous = new JButton ( "Previous" );
		next = new JButton ( "Next" );
		shopping = new ArrayList <Catalog> ( );
		defaultList = new DefaultListModel ( );
		product = new JList <Catalog> ( defaultList );
		modelImage = new JLabel ( );
		i = 0;
		
	} // End constructor.
	
	public void DnHshop ( ) {
		
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo ( null );
		setResizable ( false );
		setSize ( 600, 350 );
		setVisible ( true );
		setLayout ( null );
		Components ( );		
		
	} // End method.

	public void Components ( ) {
		
		/* Buttons. */
		modelImage.setBounds ( 350, 110, 200, 170 );
		previous.setBounds ( 50, 260, 85, 20 );
		toCart.setBounds ( 150, 260, 105, 20 );
		nameShop.setBounds ( 50, 25, 250, 25 );
		shopCart.setBounds( 425, 30, 125, 20 );
		next.setBounds ( 270, 260, 75, 20 );
		add ( shopCart );
		add ( previous );
		add ( toCart );
		add ( next );
		
		/* List. */
		nameShop.setFont ( new Font ( "", Font.BOLD, 16 ) );
		product.setBounds ( 50, 110, 200, 95 );
		add ( modelImage );
		add ( nameShop );
		add ( product );
		
		/* Set action for buttons. */
		next.addActionListener ( this );
		previous.addActionListener ( this );
		toCart.addActionListener ( this );
		shopCart.addActionListener ( this );
		
	} // End method.
	
	@Override
	public void actionPerformed ( ActionEvent e ) {
		
		if ( e.getSource ( ) == next ) {
			if ( i == 8 ) {
				i = 0;
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + Client.aList.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + Client.aList.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + Client.aList.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + Client.aList.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + Client.aList.get ( i ).getOfert ( ) );
			} else {
				i++;
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + Client.aList.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + Client.aList.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + Client.aList.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + Client.aList.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + Client.aList.get ( i ).getOfert ( ) );
			} // End if - else.
			Image ( Client.aList.get( i ).getModel ( ) );
		} // End if.
		
		if ( e.getSource ( ) == previous ) {
			if ( i == 0 ) {
				i = 8;
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + Client.aList.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + Client.aList.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + Client.aList.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + Client.aList.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + Client.aList.get ( i ).getOfert ( ) );
			} else {
				i--;
				defaultList.clear ( );
				defaultList.addElement ( "\tBrand: " + Client.aList.get ( i ).getBrand ( ) );
				defaultList.addElement ( "\nModel: " + Client.aList.get ( i ).getModel ( ) );
				defaultList.addElement ( "\n" + Client.aList.get ( i ).getDesc ( ) );
				defaultList.addElement ( "\nPrice: $" + Client.aList.get ( i ).getPrice ( ) );
				defaultList.addElement ( "\nOfert: $" + Client.aList.get ( i ).getOfert ( ) );
			} // End if - else.
			Image ( Client.aList.get( i ).getModel ( ) );
		} // End if.
		
		if ( e.getSource ( ) == toCart ) {
			shopping.add ( Client.aList.get ( i ) );
		} // End if.
		
		if ( e.getSource ( ) == shopCart ) {
			Purchase t = new Purchase ( );
			t.confirmPurchase ( );
		} // End if.
		
	} // End method.

	public static void Image ( String model ) {
		ImageIcon ic = new ImageIcon ( model + ".jpg" );
		Image im = ic.getImage ( );
		ImageIcon ic1 = new ImageIcon ( im.getScaledInstance ( 200, 170, Image.SCALE_SMOOTH ) );
		modelImage.setIcon ( ic1 );
	} // End method.
	
} // End windowGUI class.
