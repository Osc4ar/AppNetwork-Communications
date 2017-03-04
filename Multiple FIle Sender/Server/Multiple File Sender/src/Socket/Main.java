package Socket;

import java.net.*;
import java.io.*;

public class Main {

	public static void main ( String [ ] args ) {

		try {
			Server ser = new Server ( 9709 );
			ServerSocket s = ser.server ( );
			//int percentage = 0;
			s.setReuseAddress ( true );
		    System.out.println ( "\n\n\tService initialized... Waiting for client." );
		    for ( ; ; ) {
		    	Socket cl = s.accept ( );
		    	cl.setSoLinger( true , 10 );
		        System.out.println ( "\n\n\tClient connected from: " +cl.getInetAddress ( ) + ": " + cl.getPort ( ) );
		        DataInputStream dis = new DataInputStream ( cl.getInputStream ( ) );
		        int length;
		        length = dis.readInt ( );
			    for ( int i = 0 ; i < length ; i++ ) {
			    	String name = "";
			    	long recv = 0;
			    	long size = 0;
			    	name = dis.readUTF ( );
			        size = dis.readLong ( );
			        System.out.println( "\n\tReceive file: " + name + ". Length: " + size );
			       	BufferedOutputStream bos = new BufferedOutputStream ( new FileOutputStream ( name ) );
			        BufferedInputStream bis = new BufferedInputStream ( cl.getInputStream ( ) );
			        while ( recv < size ) {
			        	byte [ ] b = new byte [ 2000 ];
			            int n = bis.read ( b );
			            recv = recv + n;
			            bos.write ( b, 0, n );
			            //percentage = ( int ) ( ( recv * 100 ) / size );
			            //System.out.print ( "\n\t" + percentage + "% received." );
			        } // End of while.
			        bos.close ( );
		        } // End for.	
			    
		    } // End for.
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.
	} // End main method.

} // End Main class.
