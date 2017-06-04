package Directory;

import java.sql.SQLException;
import java.nio.file.Path;
import Database.*;
import java.io.*;
import Main.*;

/* Creates a user folder for personal and shared files. Here the program will store all
 * the documents from a user. In case of a download request, the program will search 
 * for the document in this directory, and will send back the data.
 */

public class newUser {

	private static String query;
	private static File f;
	private static Path p;
	
	public static void createDirs ( ) throws SQLException {
		
		if ( DataAccess.userdirFlag ) {
			DataAccess.userdirFlag = false;
			directory ( Operations.Readable.str [ 1 ] );
		} // End if.
		if ( DataAccess.sdirFlag ) {
			DataAccess.sdirFlag = false;
			sharedDirectory ( Operations.Readable.str [ 1 ] );
		} // End if.
		if ( DataAccess.userSD ) {
			DataAccess.userSD = false;
			userSD ( Operations.Readable.str [ 1 ] );
		} // End if.
		
	} // End method.
	
	public static void directory ( String username ) throws SQLException {
		
		f = new File ( "../../Dropbox/" + username );
		f.mkdir ( );
		p = f.toPath ( );
		query = "INSERT INTO Directory ( name, path, idUser ) VALUES ( '" + username + "', '" + p + "', " + "( SELECT iduser FROM User WHERE nickname like '" + username + "%' ) );";
		Main.da.setQuery ( query );
		
	} // End method.
	
	public static void sharedDirectory ( String username ) throws SQLException {
		
		f = new File ( "../../Dropbox/Shared " + username );
		f.mkdir ( );
		p = f.toPath ( );
		query = "INSERT INTO SharedDirectory ( name, path ) VALUES ( '" + f.getName ( ) + "', '" + p + "' )";
		Main.da.setQuery ( query );
		
	} // End method.
	
	public static void userSD ( String username ) throws SQLException {
		
		query = "INSERT INTO UserSD VALUES ( ( SELECT idUser FROM User WHERE nickname like '" + username + "%' ), ( SELECT idSD FROM SharedDirectory WHERE name like 'Shared " + username + "%' ) );"; 
		Main.da.setQuery ( query );
		
	} // End method.

} // End class.
