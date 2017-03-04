package GUI;

import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import Socket.C_Send;
import java.io.File;


public class WindowGUI extends JFrame implements ActionListener, DropTargetListener {
	
	private static final long serialVersionUID = 1L;
	static DefaultListModel defaultList;
	static ArrayList <File> aList;
	static JList <File> list;
	static JFileChooser fc;
	static JButton button1;
	static JButton button2;
	DropTarget dt;
	
	public WindowGUI ( ) {
		
		super ( "Multiple File Sender" );
		defaultList = new DefaultListModel <File> ( );
		button1 = new JButton ( "Add" );
		button2 = new JButton ( "Send" );
		aList = new ArrayList <File> ( );
		list = new JList <File> ( defaultList );
		dt = new DropTarget ( list, this );		
		fc = new JFileChooser ( );
		setLayout ( null );
	} // End Constructor. 
	
	public void Window ( ) {
		
		setSize ( 400, 300 );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo ( null );
		setResizable ( false );
		setVisible ( true );
		ButtonList ( );
		
} // End of Window method.
	
	public void ButtonList ( ) {
		
		list.setModel ( defaultList );
		list.setBounds ( 20, 20, 355, 180 );		
		button1.setBounds ( 200, 220, 75, 30 );
		button2.setBounds ( 300, 220, 75, 30 );
		add ( button1 );
		add ( button2 );
		add ( list );
		
		button1.addActionListener ( this );
		button2.addActionListener ( this );
		
	} // End of Buttons.

	public void actionPerformed ( ActionEvent e ) {
		
		int i = 0;
		
		if ( e.getSource ( ) == button1 ) {
			
			fc.setMultiSelectionEnabled (true );
			int r = fc.showOpenDialog ( null );
			defaultList.clear ( );

			if ( r == JFileChooser.APPROVE_OPTION ) {
				File [ ] f = fc.getSelectedFiles ( );
				do {
					if ( i < f.length ) {
						aList.add ( f [ i ] );
					} // End if.
					i++;
				} while ( i < aList.size ( ) );
			} // End nested if.
			for ( i = 0 ; i < aList.size ( ) ; i++ ) {
				defaultList.addElement ( aList.get ( i ) );
			} // End for.
		} // End if.
		
		if ( e.getSource ( ) == button2 ) {
			C_Send s = new C_Send ( );
			s.sendFiles ( aList );
			System.exit ( 0 );
		} // End if.
		
	} // End actionPerformed method.

	@Override
	public void dragEnter ( DropTargetDragEvent arg0 ) { }

	@Override
	public void dragExit ( DropTargetEvent arg0 ) { }

	@Override
	public void dragOver ( DropTargetDragEvent arg0 ) { }

	@Override
	public void drop ( DropTargetDropEvent dtde ) {
		dtde.acceptDrop ( DnDConstants.ACTION_COPY );
		Transferable t = dtde.getTransferable();
		DataFlavor[] df = dtde.getCurrentDataFlavors();
		for ( DataFlavor f : df ) {
			try {
				if ( f.isFlavorJavaFileListType ( ) ) {
					List <File> files = ( List <File> ) t.getTransferData ( f );
					for ( File file : files ) {
						agregar ( file.getPath ( ), file );
					} // End for.
				} // End if.
			} catch ( Exception e ) {
				JOptionPane.showMessageDialog ( null, e );
			} // End try - cathc.
		} // End for.
	} // End method.
	
	public void agregar ( String paths, File fi ) {
		defaultList.addElement ( paths );
		list.setModel ( defaultList );
		aList.add ( fi );
	} // End method.

	@Override
	public void dropActionChanged ( DropTargetDragEvent arg0 ) { }
	
} // End of WindowGUI class.
