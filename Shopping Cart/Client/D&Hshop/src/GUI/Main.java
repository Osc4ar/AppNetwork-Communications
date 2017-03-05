package GUI;

import Client.Client;

public class Main {
	
	public static void main ( String [ ] args ) {
		
		windowGUI wg = new windowGUI ( );
		Client.recvCatalog ( );
		wg.DnHshop ( );
		
		for ( int i = 0 ; i < Client.aList.size ( ) ; i++ ){
			System.out.print ( "\n\n\tProduct: " + Client.aList.get ( i ).showProduct ( ) );
		} // End for.
		
	} // End method.

} // End class.
