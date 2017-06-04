package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.*;

import javax.swing.*;

public class DropBox extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static JPanel directoryPanel;
	private static ImageIcon userPhoto;
	private static JPanel requestPanel;
	private static JPanel welcomePanel;
	private static JPanel searchPanel;
	private static ImageIcon search;
	private static JLabel lwelcome;
	private static JLabel lsearch;
	private static JLabel lphoto;
	private static Image simage;
	private static Image photo;
	
	public DropBox ( String nickname ) {
		
		// Panels.
		directoryPanel = new JPanel ( new BorderLayout ( ) );
		requestPanel = new JPanel ( new BorderLayout ( ) );
		welcomePanel = new JPanel ( new CardLayout ( ) );
		searchPanel = new JPanel ( new GridLayout ( ) );
	
		// ImageIcons.
		userPhoto = new ImageIcon ( "../../Dropbox/" + nickname + "/" + nickname + ".jpg" );
		search = new ImageIcon ( "search.png" );
		
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
		
		// Labels.
		lwelcome.setFont ( new Font ( "Arial", Font.BOLD, 20 ) );
		lwelcome.setBounds ( 80, 20, 100, 100 );
		lwelcome.setForeground ( Color.WHITE );
		lsearch.setBounds ( 200, 20, 20, 20 );
		lphoto.setBounds ( 0, 0, 60, 60 );
		lphoto.setIcon ( userPhoto );
		lsearch.setIcon ( search );
		
		// Panels.
		directoryPanel.setBackground ( Color.decode ( "0xD3D3D3" ) );
		requestPanel.setBackground ( Color.decode ( "0xB0C4DE" ) );
		searchPanel.setBackground ( Color.decode ( "0xE6E6FA" ) );
		welcomePanel.setBackground ( Color.decode ( "0x696969") );
		directoryPanel.setBounds ( 0, 100, 200, 500 );
		requestPanel.setBounds ( 200, 60, 600, 40 );
		welcomePanel.setBounds ( 60, 0, 740, 60 );
		searchPanel.setBounds ( 0, 60, 200, 40 );
		welcomePanel.add ( lwelcome );
		searchPanel.add ( lsearch );
		
		// Add to frame.
		add ( directoryPanel );
		add ( welcomePanel );
		add ( requestPanel );
		add ( searchPanel );
		add ( lphoto );
		
	} // End Components.

	public void actionPerformed ( ActionEvent e ) {
		
	} // End ActionListener.
	
} // End class.
