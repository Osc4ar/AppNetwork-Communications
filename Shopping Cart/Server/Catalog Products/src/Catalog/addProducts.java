package Catalog;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import Product.Catalog;
import java.io.File;

public class addProducts {
	
	public static ArrayList <Catalog> aList;
	public static Catalog product;
	
	public static void addNewProduct ( ) {
		
	} // End method.
	
	public static void readCatalog ( ) {
		
		File f = new File ( "Catalog.obj" );
		aList = new ArrayList <Catalog> ( );
	
		if ( !( f.exists ( ) ) ) {
			product = new Catalog ( "0001", 2999, "Man Runnig", "SUPERNOVA M", "ADIDAS", false, true );
			aList.add ( product );
			product = new Catalog ( "0010", 1499, "Man Running", "COSMIC M", "ADIDAS", false, true );
			aList.add ( product );
			product = new Catalog ( "0100", 1199, "Man Runnig", "DURAMO LITE M", "ADIDAS", false, true );
			aList.add ( product );
			product = new Catalog ( "0101", 4499, "Man Football", "ACE TANGO 17.1 TR", "ADIDAS", true, true );
			aList.add ( product );
			product = new Catalog ( "0110", 2799, "Outdoor", "TERREX AGRAVIC SPEED", "ADIDAS", true, true );
			aList.add ( product );
			product = new Catalog ( "0111", 1399, "Woman Runnig", "GALAXY TRAIL W", "ADIDAS", false, true );
			aList.add ( product );
			product = new Catalog ( "1000", 1499, "Woman Runnig", "COSMIC W", "ADIDAS", false, true );
			aList.add ( product );
			product = new Catalog ( "1001", 1399, "Woman Runnig", "DURAMO 8 W", "ADIDAS", false, true );
			aList.add ( product );
			product = new Catalog ( "1010", 2999, "Woman Runnig", "ADIZERO ADIOS W", "ADIDAS", true, true );
			aList.add ( product );
			product = new Catalog ( "1011", 2699, "Woman Runnig", "TEMPO 8 SSF", "ADIDAS", false, true );
			aList.add ( product );
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream ( new FileOutputStream ( "Catalog.obj" ) );
				for ( int i = 0 ; i < aList.size ( ) ; i++ ) {
					oos.writeObject ( aList.get ( i ) );
				} // End for.
				oos.close ( );
			} catch ( Exception e ) {
				e.printStackTrace ( );
			} // End try - catch.
			
		} else {
			
			try {
				ObjectInputStream ois = new ObjectInputStream ( new FileInputStream ( "Catalog.obj" ) );
				for ( int i = 0 ; i < 10 ; i++ ) {
					product = ( Catalog ) ois.readObject ( );
					aList.add ( product );
				} // End for.
				ois.close ( );
			} catch ( Exception e ) {
				e.printStackTrace ( );
			} // End try - catch.
			
		} // End if - else.
		
	} // End method.

} // End method.
