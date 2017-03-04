package Socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	Socket cl;
	String host;
	int port;
	
	public Client ( String h, int port ) {
		this.host = h;
		this.port = port;
	} // End Constructor.
	
	public String getHost ( ) {
		return this.host;
	} // End getHost method.
	
	public int getPort ( ) {
		return this.port;
	} // End getPort method.
	
	public Socket client ( ) throws UnknownHostException, IOException {
		return this.cl = new Socket ( this.host, this.port );
	} // End S_Client method.
		
} // End Client class.
