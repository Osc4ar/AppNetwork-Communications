package Socket;

import java.util.ArrayList;
import Socket.Client;
import java.io.*;
import java.net.*;

public class C_Send {

	public void sendFiles ( ArrayList <File> aList ) {
		
		try {
			Client c = new Client ( "127.0.0.1", 9709 );
			Socket cl = c.client ( );
			//int percentage = 0;
			int i;
			
			File [ ] f = new File [ aList.size ( ) ];
			for ( i = 0 ; i < aList.size ( ) ; i++ ) {
				f [ i ] = aList.get ( i );
			} // End for.
			
			for ( i = 0 ; i < f.length ; i++ ) {
				String name = f [ i ].getName ( );
				String path = f [ i ].getPath ( );
				long size = f [ i ].length ( );
				DataOutputStream dos = new DataOutputStream ( cl.getOutputStream ( ) );
				if ( i <= 0 ) {
					int f_length = f.length;
					dos.writeInt ( f_length );
					dos.flush ( );
				} // End if.
				dos.writeUTF ( name );
				dos.flush ( );
				dos.writeLong ( size );
				DataInputStream dis = new DataInputStream ( new FileInputStream ( path ) );
				long sent = 0;
				while ( sent < size ) {
					byte [ ] b = new byte [ 2000 ];
					int n = dis.read ( b );
					sent = sent + n;
					dos.write ( b, 0, n );
					dos.flush ( );
					//percentage = ( int ) ( ( sent * 100 ) / size );
			        //System.out.print ( "\n\t" + percentage + "% sent." );
				} // End while.
				System.out.println ( "\n\n\tFile sended...\n" );
				dis.close ( );
				//dos.close ( );
			} // End for.
			cl.close ( );
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.

	} // End sendFiles method.
} // End of C_Send class.
