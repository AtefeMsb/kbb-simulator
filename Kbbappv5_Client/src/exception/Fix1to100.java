/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package exception;

import java.util.Scanner;

// This class contains fix method for UTIL Package exceptions.
public class Fix1to100 {

	public String fixMissingAutoPrice() {	// fix(2)
		
		Scanner scanner = new Scanner(System.in);
		String priceInString;
		boolean priceFound = false;
		
		do {
			System.out.print("Please enter the automobile price: ");
			priceInString = scanner.nextLine();
			try {
				Integer.parseInt(priceInString);
				priceFound = true;
			} catch (NumberFormatException nfe) {
				priceFound = false;
			}
			
		} while (!priceFound);
		
		scanner.close();
		return priceInString;	
	}
	
	public String fixMissingAutoName() {
		return "The name of the model";
	}
	
	public String fixMissingOptionSetLength() {
		return "The length of the OptionSet";
	}
	
	public String fixMissingOption() {
		return "The option of the model";
	}
	
	public void fixSerialization() {
		System.out.println("For serialization please try later!");
	}
}