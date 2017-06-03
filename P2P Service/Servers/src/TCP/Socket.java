package TCP;

/* This class creates and initialize a TCP/IP socket. Receive from the client a 
 * download request, then the socket send the data and wait for another one.
 */

import java.net.StandardSocketOptions;
import java.nio.channels.*;
import java.util.Iterator;
import java.net.*;

public class Socket {

	protected Iterator <SelectionKey> it;
	public ServerSocketChannel ssc;
	protected SelectionKey k;
	protected boolean sFlag;
	protected Selector sel;
	protected String host;
	protected int port;
	
	public Socket ( String host, int port, boolean sFlag ) {
		
		this.sFlag = sFlag;
		this.host = host;
		this.port = port;
		
	} // End constructor.
	
	public Socket ( String host, boolean sFlag ) {
		
		this.sFlag = sFlag;
		this.host = host;
		
	} // End constructor.
	
	public void init ( int port )  {
		
		try {
			this.port = port;
			ssc = ServerSocketChannel.open ( );
			ssc.configureBlocking ( false );
			ssc.setOption ( StandardSocketOptions.SO_REUSEADDR, true );
			ssc.socket ( ).bind ( new InetSocketAddress ( getPort ( ) ) );
			sel = Selector.open ( );
			ssc.register ( sel, SelectionKey.OP_ACCEPT );
			sFlag = true;
		} catch ( Exception e ) {
			sFlag = false;
		} // End try - catch.
		
	} // End method.
	
	public void start ( ) {
		
		try {
			System.out.println ( "\n\t\tTCP Service initialized: " + ssc.socket ( ).getInetAddress ( ).getHostAddress ( ) + " : " + ssc.socket ( ).getLocalPort ( ) + "." );
			for ( ; ; ) {
				try {
					sel.select ( );
					it = sel.selectedKeys ( ).iterator ( );
					while ( it.hasNext ( ) ) {
						k = ( SelectionKey ) it.next ( );
						it.remove ( );
						if ( k.isAcceptable ( ) ) {
							continue;
						} // End if.
						if ( k.isReadable ( ) ) {
							continue;
						} // End if.
						if ( k.isWritable ( ) ) {
							continue;
						} // End if.
					} // End while.
				} catch ( Exception e1 ) { }
			} // End forever.
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.
		
	} // End method.
	
	/* Getters. */
	public boolean getFlag ( ) { return sFlag; }
	public String getHost ( ) { return host; }
	public int getPort ( ) { return port; }
	
} // End class.
