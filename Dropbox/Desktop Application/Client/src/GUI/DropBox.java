package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import javax.swing.*;
import java.io.File;
import java.awt.*;

public class DropBox extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static JList <String> directory;
	private static JTextField searchField;
	private static JPanel directoryPanel;
	private static JScrollPane scroller1;
	private static JScrollPane scroller;
	private static ImageIcon userPhoto;
	private static JPanel requestPanel;
	private static JPanel welcomePanel;
	private static JPanel searchPanel;
	private static JList <File> files;
	private static ImageIcon search;
	private static JLabel lwelcome;
	private static JLabel lsearch;
	private static JLabel lphoto;
	private static Image simage;
	private static Image photo;
	
	public DropBox ( String nickname ) {
		
		// Panels.
		searchPanel = new JPanel ( new FlowLayout ( FlowLayout.LEFT ) );
		directoryPanel = new JPanel ( new GridLayout ( 1, 1 ) );
		requestPanel = new JPanel ( new BorderLayout ( ) );
		welcomePanel = new JPanel ( new CardLayout ( ) );
	
		// ImageIcons.
		userPhoto = new ImageIcon ( "../../Dropbox/" + nickname + "/" + nickname + ".jpg" );
		search = new ImageIcon ( "search.png" );
		
		// Lists.
		directory = new JList <String> ( new DefaultListModel <String> ( ) );
		files = new JList <File> ( new DefaultListModel <File> ( ) );
		
		// Scroll.
		scroller1 = new JScrollPane ( directory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scroller = new JScrollPane ( files, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

		// Text fields.
		searchField = new JTextField ( "Search" );
		
		//Images.
		photo = userPhoto.getImage ( );
		simage = search.getImage ( );
		
		// Labels.
		lwelcome = new JLabel ( "   " + nickname );
		lsearch = new JLabel ( );
		lphoto = new JLabel ( );
		
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
		
		// ImageIcons.
		userPhoto = new ImageIcon ( photo.getScaledInstance ( 60, 60, Image.SCALE_SMOOTH ) );
		search = new ImageIcon ( simage.getScaledInstance ( 20, 20, Image.SCALE_SMOOTH ) );
		
		// Text fields.
		searchField.setBackground ( Color.decode ( "0xE6E6FA" ) );
		searchField.setBounds ( 40, 70, 160, 20 );
		
		// Lists.
		directory.setBackground ( Color.decode ( "0xD3D3D3" ) );
		directory.setBounds ( 20, 120, 180, 440 );
		files.setBounds ( 210, 110, 570, 450 );
		files.setBackground ( Color.WHITE );
		
		// Scroll.
		scroller1.setBounds ( 10, 120, 190, 450 );
		scroller.setBounds ( 210, 110, 580, 460 );
		
		// Labels.
		lwelcome.setFont ( new Font ( "Arial", Font.BOLD, 20 ) );
		lwelcome.setBounds ( 80, 20, 100, 100 );
		lwelcome.setForeground ( Color.WHITE );
		lsearch.setBounds ( 10, 70, 20, 20 );
		lphoto.setBounds ( 0, 0, 60, 60 );
		lphoto.setIcon ( userPhoto );
		lsearch.setIcon ( search );
		
		// Panels.
		directoryPanel.setBackground ( Color.decode ( "0xD3D3D3" ) );
		requestPanel.setBackground ( Color.decode ( "0xB0C4DE" ) );
		searchPanel.setBackground ( Color.decode ( "0xE6E6FA" ) );
		welcomePanel.setBackground ( Color.decode ( "0x696969") );
		directoryPanel.setBounds ( 0, 110, 200, 460 );
		requestPanel.setBounds ( 200, 60, 600, 40 );
		welcomePanel.setBounds ( 60, 0, 740, 60 );
		searchPanel.setBounds ( 0, 60, 200, 40 );
		directoryPanel.add ( scroller1 );
		searchPanel.add ( searchField );
		welcomePanel.add ( lwelcome );
		searchPanel.add ( lsearch );
		
		// Add to frame.
		add ( directoryPanel );
		add ( welcomePanel );
		add ( requestPanel );
		add ( searchPanel );
		add ( scroller );
		add ( lphoto );
		
	} // End Components.

	public void actionPerformed ( ActionEvent e ) {
		
	} // End ActionListener.
	
} // End class.
