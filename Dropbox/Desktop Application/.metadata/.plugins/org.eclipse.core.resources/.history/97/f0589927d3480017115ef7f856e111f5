package Main;

import java.net.StandardSocketOptions;
import Operations.Writable;
import java.nio.channels.*;
import java.util.Iterator;
import Database.*;

import java.net.*;

public class Main {
	
	public static Iterator <SelectionKey> it;
	public static ServerSocketChannel ssc;
	public static SelectionKey k;
	public static DataAccess da;
	public static Selector sel;
	public static int port;
	
	public static void Init ( ) throws Exception {
		
		ssc = ServerSocketChannel.open ( );
		ssc.configureBlocking ( false );
		ssc.setOption ( StandardSocketOptions.SO_REUSEADDR, true );
		port = 9709;
		ssc.socket ( ).bind ( new InetSocketAddress ( port ) ); 
		sel = Selector.open ( );
		ssc.register ( sel, SelectionKey.OP_ACCEPT );
		da = new DataAccess ( );
		
		// Initialization of all boolean flags.
		DataAccess.userdirFlag = false;
		DataAccess.sdirFlag = false;
		Writable.signError = false;
		DataAccess.userSD = false;
		Writable.signFlag = false;
		Writable.logFlag = false;
		
	} // End Init. 
	
	public static void main ( String [ ]  args ) {
		
		try {
			Init ( );
			for ( ; ; ) {
				try {
					sel.select ( );
					it = sel.selectedKeys ( ).iterator ( );
					while ( it.hasNext ( ) ) {
						k = ( SelectionKey ) it.next ( );
						it.remove ( );
						if ( k.isAcceptable ( ) ) {
							Operations.Acceptable.accept ( ssc );
							continue;
						} // End if.
						if ( k.isReadable ( ) ) {
							Operations.Readable.read ( );
							k.interestOps ( SelectionKey.OP_WRITE );
							continue;
						} // End if.
						if ( k.isWritable ( ) ) {
							Operations.Writable.write ( );
							k.interestOps ( SelectionKey.OP_READ );
							continue;
						} // End if.
					} // End while.
				} catch ( Exception e ) { } // End try - catch.
			} // End forever.
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.
		
	} // End main.

} // End class. 
