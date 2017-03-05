package Client;

import GUI.windowGUI;
//import java.net.*;
import java.io.*;

public class Ticket {

	public static void ticket ( ) {
		
		try {
			DataOutputStream dos = new DataOutputStream ( Client.cl.getOutputStream ( ) );
			DataInputStream dis = new DataInputStream ( Client.cl.getInputStream ( ) );
			int size = windowGUI.shopping.size ( );
			dos.writeInt ( size );
			dos.flush ( );
			for ( int i = 0 ; i < size ; i++ ) { 
				dos.writeUTF ( windowGUI.shopping.get ( i ).getID ( ) );
			} // End for.
			long recv = 0;
			String name = dis.readUTF ( );
			long sizeF = dis.readLong ( );
			BufferedOutputStream bos = new BufferedOutputStream ( new FileOutputStream ( name ) );
	        BufferedInputStream bis = new BufferedInputStream ( Client.cl.getInputStream ( ) );
	        while ( recv < sizeF ) {
	          byte [ ] b = new byte [ 2000 ];
	          int n = bis.read ( b );
	          recv = recv + n;
	          bos.write ( b, 0, n );
	        } // End of while.
	        dis.close ( );
	        bos.close ( );
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.
		
	} // End method.

} // End class.
