package Operations;

import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.sql.ResultSet;

import Database.DataAccess;
import Main.*;

public class Readable {

	private static SocketChannel ch;
	private static boolean valid;
	public static String [ ] str;
	private static String action;
	private static ByteBuffer b;
	private static boolean flag;
	private static int n;
	
	/* The method read receives the data from the client. */
	
	public static void read ( ) throws Exception {
		
		ch = ( SocketChannel ) Main.k.channel ( );
		b = ByteBuffer.allocate ( 100 );
		b.clear ( );
		n = ch.read ( b );
		b.flip ( );
		action = new String ( b.array ( ), 0, n );
		str = action.split ( ":" );
		
		if ( str [ 0 ].equalsIgnoreCase ( "<login>" ) ) {
			login ( );
		} // End if.
		if ( str [ 0 ].equalsIgnoreCase ( "<signup>" ) ) {
			signup ( );
		} // End if.
		
	} // End read.
	
	/* Every time a user makes a log in, the client socket sends to the server the
	 * email and the password in that order, the function login compare the data
	 * and evaluate if the user it's on the database or if isn't.
	 */
	
	public static void login ( ) throws Exception {
				
		ResultSet rs = ( ResultSet ) Main.da.getQuery ( "SELECT * FROM User" );
		while ( rs.next ( ) ) {
			String password = rs.getString ( "pass" );
			String email = rs.getString ( "email" );
			if ( email.equalsIgnoreCase ( str [ 1 ] ) && password.equals ( str [ 2 ] ) ) {
				System.out.println ( "\n\tWelcome back: " + rs.getString ( "nickname" ) + "\n" );
				Writable.nickname = rs.getString ( "nickname" );
				Writable.logFlag = true;
				break;
			} // End if.
		} // End while.
		
	} // End method.
	
	/* The method sign up receive from the the method read a string of data specifying the name,
	 * nickname, last name, email and password for the new user, sends the info to the
	 * method DataAccess and compares if the user exist on the database or not.
	 */
	
	public static void signup ( ) throws Exception {
				
		valid = strValidation ( );
		if ( valid ) {
			DataAccess.userdirFlag = true;
			DataAccess.sdirFlag = true;
			DataAccess.userSD = true;
			Main.da.setQuery ( "INSERT INTO User ( nickname, name, lastname, email, pass )" + "VALUES ( '" + str [ 1 ] + "', '" + str [ 2 ] + "', '" + str [ 3 ] + "', '" + str [ 4 ] + "', '" + str [ 5 ]+ "' )" );
		} else {
			Writable.signError = true; 
		} // End if - else.
	
	} // End method.
	
	public static boolean strValidation ( ) {
		
		if ( str [ 1 ].equalsIgnoreCase ( "Choose a Nickname" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 2 ].equalsIgnoreCase ( "First Name" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 3 ].equalsIgnoreCase ( "Last Name" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 4 ].equalsIgnoreCase ( "Your Email" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 5 ].equalsIgnoreCase ( "Password" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		
		if ( str [ 1 ].equalsIgnoreCase ( "" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 2 ].equalsIgnoreCase ( "" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 3 ].equalsIgnoreCase ( "" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 4 ].equalsIgnoreCase ( "" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		if ( str [ 5 ].equalsIgnoreCase ( "" ) ) {
			return false;
		} else {
			flag = true;
		} // End if - else.
		
		return flag;
		
	} // End method.
	
} // End class.
