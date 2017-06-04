package Directory;

import java.nio.file.Path;
import java.sql.SQLException;
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
