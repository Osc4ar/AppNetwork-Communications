package Database;

import java.sql.*;

public class DataAccess {
	
	private static String password = "David";
	private static String db = "Dropbox";
	private static String user = "root";
	private static String url = "jdbc:mysql://localhost/" + db;
	private static Connection con = null;
	
	public DataAccess ( ) throws ClassNotFoundException, SQLException {
		
		Class.forName ( "com.mysql.jdbc.Connection" );
		con = ( Connection ) DriverManager.getConnection ( url, user, password );
		if ( con != null ) {
			System.out.println ( "\n\tConnection to " + url + " database... OK.");
		} // End if.
		
	} // End constructor.
	
	public ResultSet getQuery ( String query ) throws SQLException {
		
		Statement s = ( Statement ) con.createStatement ( );
		ResultSet rs = s.executeQuery ( query );
		
		return rs;
		
	} // End getQuery.
	
	public void setQuery ( String query ) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement ( query );
		//Statement s = ( Statement ) con.createStatement ( );
		ps.executeUpdate ( query );
		
	} // End setQuery.
	
} // End class.
