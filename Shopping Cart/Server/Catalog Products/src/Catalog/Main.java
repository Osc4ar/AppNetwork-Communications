package Catalog;

import java.util.Scanner;

public class Main {
	
	private static Scanner sc;
	
	public static void main ( String [ ] args ) {
		
		String answer;
		sc = new Scanner ( System.in );
		
	    System.out.print ( "\n\tTo add a new product enter <yes>. \n\tOr <no> to read and send the catalog: " );
	    answer = sc.nextLine ( );
	    answer = answer.toLowerCase ( );
	    
	    if ( !( answer.equals( "no" ) ) == true ) {
	    	System.out.print ( "\n\tAdd a new Product.\n\n" );
	    	addProducts.addNewProduct ( );
	    } else {
	    	addProducts.readCatalog ( );
	    	Server.DnHshop ( );
	    } // End if - else.
	} // End main.
} // End class.
