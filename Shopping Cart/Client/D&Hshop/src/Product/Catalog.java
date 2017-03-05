package Product;

import java.io.Serializable;

public class Catalog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	boolean existence;
	boolean discount;
	String brand;
	String model;
	String desc;
	String id;
	int price;
	int ofert;
	
	public Catalog ( String id, int p, String desc, String m, String b, boolean d, boolean e ) {
		
		this.id = id;
		this.price = p;
		this.desc = desc;
		this.model = m;
		this.brand = b;
		this.discount = d;
		this.existence = e;
		if ( d == true ) {
			this.ofert = price - ( ( int ) ( price * 0.25 ) );
		} else {
			this.ofert = 0;
		} // End if - else.

	} // End constructor.

	public Catalog ( ) { } // End constructor

	/* SETTERS. */

	public void setID ( String id ) {
		this.id = id;
	} // End method.

	public void setPrice ( int p ) {
		this.price = p;
	} // End method.

	public void setModel ( String m ) {
		this.model = m;
	} // End method.

	public void setDesc ( String desc ) {
		this.desc = desc;
	} // End method.

	public void setBrand ( String b ) {
		this.brand = b;
	} // End method.

	public void setDiscount ( boolean d ) {
		if ( d == true ) {
			this.ofert = price - ( ( int ) ( price * 0.25 ) );
		} else {
			this.ofert = 0;
		} // End if - else.
	} // End method.

	public void setExistence ( boolean e ) {
		this.existence = e;
	} // End of method.

	/* GETTERS. */

	public String getID ( ) {
		return this.id;
	} // End method.

	public int getPrice ( ) {
		return this.price;
	} // End method.

	public int getOfert ( ) {
		return this.ofert;
	} // End method.

	public String getModel ( ) {
		return this.model;
	} // End method.

	public String getDesc ( ) {
		return this.desc;
	} // End method.

	public String getBrand ( ) {
		return this.brand;
	} // End method.

	public boolean getDiscount ( ) {
		return this.discount;
	} // End method.

	public boolean getExistence ( ) {
		return this.existence;
	} // End of method.

	public String showProduct ( ) {
		return "\n\n\tBrand: " + brand + ". \n\tModel: " + model + ". \n\tDescription: " + desc + ". \n\tPrice: $" + price
			   + ". \n\tOfert: $" + ofert + ". \n\tExistence: " + existence + ". \n\tIdentifier: " + id + ".";
	} // End method.
  
} // End class.