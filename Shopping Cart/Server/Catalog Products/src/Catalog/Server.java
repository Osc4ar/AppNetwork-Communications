package Catalog;

import Product.Ticket;
import java.net.*;
import java.io.*;

public class Server {
	
	private static ServerSocket s;
	public static String [ ] idp;
	public static int invoiceNumber;
	
	public static void DnHshop ( ) {
		
		invoiceNumber = 0;
		
		try {
			int port = 9707;
			s = new ServerSocket ( port );
			s.setReuseAddress ( true );
			System.out.println ( "\n\n\tServer initialized... waiting for requests." );
			for ( ; ; ) {
				Socket cl = s.accept ( );
				DataOutputStream dos = new DataOutputStream ( cl.getOutputStream ( ) );
				DataInputStream dis = new DataInputStream ( cl.getInputStream ( ) );
				ObjectOutputStream oos = new ObjectOutputStream ( cl.getOutputStream ( ) );
				dos.writeInt ( addProducts.aList.size (  ) );
				dos.flush ( );
				for ( int i = 0 ; i < addProducts.aList.size ( ) - 1 ; i++ ) {
					oos.writeObject ( addProducts.aList.get ( i ) );
					oos.flush ( );
				} // End for.
				int size = dis.readInt ( );
				idp = new String [ size ];
				for ( int i = 0 ; i < size ; i++ ) {
					idp [ i ] = dis.readUTF ( );
				} // End for.
				for ( int i = 0 ; i < size ; i++ ) {
					System.out.println( idp [ i ] );
				} // End for.	
				Ticket.ticket ( );
				invoiceNumber++;
				String name = Ticket.f.getName ( );
				String path = Ticket.f.getAbsolutePath ( );
				long sizeF = Ticket.f.length ( );
				dos.writeUTF ( name );
				dos.flush ( );
				dos.writeLong ( sizeF );
				dos.flush ( );
				DataInputStream dis1 = new DataInputStream ( new FileInputStream ( path ) );
				long sent = 0;
				while ( sent < sizeF ) {
					byte [ ] b = new byte [ 2000 ];
					int n = dis1.read ( b );
					sent = sent + n;
					dos.write ( b, 0, n );
					dos.flush ( );
				} // End while.
				dos.close ( );
				oos.close ( );
				dis.close ( );
				dis1.close ( );
				cl.close ( );
			} // End for-ever.
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.
		
	} // End method.

} // End class.
