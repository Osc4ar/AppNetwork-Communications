package Database;

import java.sql.*;

import Directory.newUser;
import Operations.*;

public class DataAccess {
	
	private static String password = "David";
	private static String db = "Dropbox";
	private static String user = "root";
	private static String url = "jdbc:mysql://localhost/" + db;
	private static Connection con = null;
	public static boolean userdirFlag;
	public static boolean sdirFlag;
	public static boolean userSD;
	
	public DataAccess ( ) throws ClassNotFoundException, SQLException {
		
		Class.forName ( "com.mysql.jdbc.Connection" );
		con = ( Connection ) DriverManager.getConnection ( url, user, password );
		if ( con != null ) {
			System.out.println ( "\n\tConnection to " + url + " database... OK.");
		} // End if.
		
	} // End constructor.
	
	public void reconnect ( ) throws SQLException {
		
		con = ( Connection ) DriverManager.getConnection ( url, user, password );
		if ( con != null ) {
			System.out.println ( "\n\tConnection to " + url + " database... OK.");
		} // End if.
		
	} // End method.
	
	public ResultSet getQuery ( String query ) throws SQLException {
		
		Statement s = ( Statement ) con.createStatement ( );
		ResultSet rs = s.executeQuery ( query );
		
		return rs;
		
	} // End getQuery.
	
	public void setQuery ( String query ) {
		
		try { 
			PreparedStatement ps = con.prepareStatement ( query );
			ps.executeUpdate ( query );
			newUser.createDirs ( );
			Writable.signFlag = true;
		} catch ( SQLException sql ) {
			sql.printStackTrace ( );
			Writable.signFlag = false;
			Writable.signError = true;
		} // End try - catch.
		
	} // End setQuery.
	
} // End class.
