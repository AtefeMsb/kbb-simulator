/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package model;

import java.io.Serializable;

public class Option implements Serializable{

	String name;
	int price;
	
	// Constructor
	public Option() {
		this(null, 0);
	}
	
	public Option(String name) {
		this(name, 0);
	}

	public Option(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Option)) {
	        return false;
	    }

	    Option that = (Option) other;

	    // Custom equality check here.
	    return this.name.equals(that.name)
	        && this.price ==that.price;
	}
	
	// getters
	protected String getName() {
		return name;
	}

	protected int getPrice() {
		return price;
	}

	// Setters
	protected void setName(String name) {
		this.name = name;
	}

	protected void setPrice(int price) {
		this.price = price;
	}
	
	// toString
	protected void print() {
		System.out.println(this.toString());
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		String LINE_BREAK = System.getProperty("line.separator"); // \n
		String TAB = "\t";
		String className = this.getClass().getName();
					
		str.append(className).append(" Object {").append(LINE_BREAK)
			.append(TAB).append("name=").append(name).append(LINE_BREAK)
			.append(TAB).append("price=").append(price).append(LINE_BREAK)
			.append("}");

		return str.toString();
	}
	
}