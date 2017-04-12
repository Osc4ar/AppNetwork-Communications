package Main;

import ClientSocket.Client;
import GUI.Init;

public class Main {
	
	public static void main ( String [ ] args ) {
		Client cl = new Client ( );
		cl.Launch ( );
		Init in = new Init ( );
		in.Launch ( );
	} // End main.

} // End class.
