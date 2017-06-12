/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package adapter;

public interface UpdateAuto {
	
	public abstract void updateOptionSetName(String modelName, String optionSet, String newName);
	public abstract void updateOptionPrice(String modelName, String optionSetName, String optionName, int newPrice);
	
}
