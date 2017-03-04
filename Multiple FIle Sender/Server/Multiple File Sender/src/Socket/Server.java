package Socket;

import java.io.IOException;
import java.net.*;

public class Server {
	
	ServerSocket s;
	int port;
	
	public Server ( int p ) {
		this.port = p;
	} // End Constructor.
	
	public int getPort ( ) {
		return this.port;
	} // End getPort method.
	
	public ServerSocket server ( ) throws IOException {
		return this.s = new ServerSocket ( this.port ); 
	} // End server class.

} // End Server class.
