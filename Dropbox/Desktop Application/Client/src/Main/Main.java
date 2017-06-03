package Main;

import Socket.Client;
import GUI.Init;

public class Main {
	
	public static void main ( String [ ] args ) throws Exception {
		
		Client c = new Client ( );
		c.Launch ( );
		Init i = new Init ( );
		i.Launch ( );
		
	} // End main.

} // End class.
