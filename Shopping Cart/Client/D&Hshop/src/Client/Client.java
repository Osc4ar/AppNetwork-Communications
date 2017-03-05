package Client;

import java.io.ObjectInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.net.Socket;
import Product.Catalog;

public class Client {
	
	public static ArrayList <Catalog> aList;
	public static Catalog product;
	public static Socket cl;
	
	public static void recvCatalog ( ) {
		
		aList = new ArrayList <Catalog> ( );
		
		try {
			String host = "127.0.0.1";
			int port = 9707;
			cl = new Socket ( host, port );
			ObjectInputStream ois = new ObjectInputStream ( cl.getInputStream ( ) );
			DataInputStream dis = new DataInputStream ( cl.getInputStream ( ) );
			int size = dis.readInt ( );
			for ( int i = 0 ; i < size - 1 ; i++ ) {
				product = ( Catalog ) ois.readObject ( );
				aList.add ( product );
			} // End for.
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.
	} // End method.

} // End class.
