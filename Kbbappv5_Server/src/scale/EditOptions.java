/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package scale;

import model.Automobile;

public class EditOptions {
	Automobile automobile;
	boolean available;
	String[] info; // values that might change -- get it from Editor
	/**
	 *  In info Arrays:
	 * 	String optionSetName; // 0
	 *  String optionSetNewName; // 1
	 *	String optionName; // 2
	 *	int newPrice; // 3
	 */

	public EditOptions(Automobile automobile) {
		this.automobile = automobile;
		available = true;
	}
	
	public synchronized void updateOptionPrice(String[] info) {
		System.out.println("Entering updateOptionPrice method ");

		while (available == false) { 
			try {
				// wait to another thread sends notifyAll()
				System.out.println("updateOptionPrice Waiting ");
				wait();
			} catch (InterruptedException e) {
				System.out.println("updateOptionPrice done waiting ");
			}
		}
		
		available = false;
		// Now it can update the option price
		boolean result = automobile.updateOptionPrice(info[0], info[2], Integer.parseInt(info[3]));
		
		if (result) {
			System.out.println("***Option:" + info[2] + " , New Price:" + Integer.parseInt(info[3]) + "***");
		} else {
			System.out.println("****Thread 0 was not able to update the Option price****");
		}
		
		System.out.println("updateOptionPrice Notifyall ");
		System.out.println("updateOptionPrice Done! ");
		available = true;
		notifyAll();
	}
	
	public synchronized void updateOptionSetName(String[] info) {
		System.out.println("Entering updateOptionSetName method ");

		while (available == false) { 
			try {
				// wait to another thread sends notifyAll()
				System.out.println("updateOptionSetName Waiting ");
				wait();
			} catch (InterruptedException e) {
				System.out.println("updateOptionSetName done waiting ");
			}
		}
		
		this.available = false;
		// Now it can update the optionset name
		boolean result = automobile.updateOptionSetName(info[0], info[1]);
		
		if (result) {
			System.out.println("***OptionSet:" + info[0] + " , New Name:" + info[1] + "***");
		} else {
			System.out.println("****Thread 1 was not able to update the OptionSet name****");
		}
		
		System.out.println("updateOptionSetName Notifyall ");
		System.out.println("updateOptionSetName Done! ");
		available = true;
		notifyAll();
	}
	//**************************************************************************************
	// Uncomment for Step 3 - showing data corruption
	/**
	public void updateOptionPrice(String[] info) {
		System.out.println("Entering updateOptionPrice method ");
		boolean result = true;
		
		if (available == true) { 
			available = false;
			// Now it can update the option price
			result = automobile.updateOptionPrice(info[0], info[2], Integer.parseInt(info[3]));
		}
		if (result) {
			System.out.println("***Option:" + info[2] + " , New Price:" + Integer.parseInt(info[3]) + "***");
		} else {
			System.out.println("****Thread 0 was not able to update the Option price****");
		}
		
		System.out.println("updateOptionPrice Notifyall ");
		System.out.println("updateOptionPrice Done! ");
		available = true;
	}
	
	public void updateOptionSetName(String[] info) {
		System.out.println("Entering updateOptionSetName method ");
		boolean result = true;
		if (available == true) { 
			this.available = false;
			// Now it can update the optionset name
			result = automobile.updateOptionSetName(info[0], info[1]);
		}
		if (result) {
			System.out.println("***OptionSet:" + info[0] + " , New Name:" + info[1] + "***");
		} else {
			System.out.println("****Thread 1 was not able to update the OptionSet name****");
		}
		
		System.out.println("updateOptionSetName Done! ");
		available = true;
	}
	*/
}