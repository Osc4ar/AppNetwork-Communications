package Product;

import Catalog.addProducts;
import java.util.ArrayList;
import java.io.File;
import Catalog.Server;
import java.io.FileWriter;
import java.text.DecimalFormat;

public class Ticket {

	public static File f;
	
	public static void ticket ( ) {
		
		f = new File ( "D&Hshop Ticket.txt" );
		ArrayList <Catalog> s = new ArrayList <Catalog> ( );
		DecimalFormat df = new DecimalFormat ( "0.00" );
		double pTotal = 0;
		double total = 0;
		double taxes = 0;
		
		try {
			FileWriter fw = new FileWriter ( f );
			if ( !f.exists ( ) ) {
				f.createNewFile ( );
			} // End if.
			f.createNewFile ( );
			fw.write ( "\n\n\t\t\t\tD&Hshop... Buy on the Cloud.\n" );
			fw.write ( "\n\n\t\tINVOICE: # " + Server.invoiceNumber + ".\n\n" );
			for ( int i = 0 ; i < Server.idp.length ; i++ ) {
				for ( int j = 0 ; j < addProducts.aList.size ( ) ; j++ ) {
					if ( Server.idp [ i ].equals ( addProducts.aList.get ( j ).id )  ) {
						s.add ( addProducts.aList.get( j ) );
						System.out.println ( addProducts.aList.get ( j ).showProduct ( ) );
					} else {
						continue;
					} // End if - else.
				} // End nested for.
			} // End for
			for ( int i = 0 ; i < s.size ( ) ; i++ ) {
				fw.write ( "\n\n\t\tBrand: " + s.get ( i ).brand );
				fw.write ( "\n\t\tModel: " + s.get ( i ).model );
				fw.write ( "\n\t\tDescription: " + s.get ( i ).desc );
				if ( s.get ( i ).discount == true ) {
					fw.write ( "\n\n\t\tPrice: \t\t\t\t\t$ " + df.format ( s.get ( i ).ofert ) ); 
					total = total + s.get ( i ).ofert;
					pTotal = pTotal + ( s.get ( i ).ofert / 1.16 );
					taxes = taxes + ( ( s.get ( i ).ofert / 1.16 ) * 0.16 );
				} else {
					fw.write ( "\n\n\t\tPrice: \t\t\t\t\t$ " + df.format ( s.get ( i ).price ) ); 
					total = total + s.get ( i ).price;
					pTotal = pTotal + ( s.get ( i ).price / 1.16 );
					taxes = taxes + ( ( s.get ( i ).price / 1.16 ) * 0.16 );
				} // End if - else.
			} // End for.
			fw.write ( "\n\n\n\n\t\tSubtotal: \t\t\t\t\t\t $ " + df.format( pTotal ) );
			fw.write ( "\n\t\tTaxes: \t\t\t\t\t\t $ " + df.format ( taxes  ) );
			fw.write ( "\n\t\t\t\t\t\t\t\t     ---------------" );
			fw.write ( "\n\t\tTotal: \t\t\t\t\t\t $ " + df.format ( total ) );
			fw.close ( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		} // End try - catch.
		
	} // End Method
} // End class.
