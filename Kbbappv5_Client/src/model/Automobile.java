/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package model;
import java.io.Serializable;
import java.util.ArrayList; 

public class Automobile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String make;
	String model;
	int year;
	ArrayList<OptionSet> optionSets;
	ArrayList<Option> userChoices;
	
	// Constructors
	public Automobile() {
		this(null, null, 0, 0);
	}
	
	public Automobile(String make, String model, int year, int optionSetSize) {
		this.make = make;
		this.model = model;
		this.year = year;
		
		if (optionSetSize < 1) {
			optionSets = null;
			return;
		}

		optionSets = new ArrayList<OptionSet>(optionSetSize);
		userChoices = new ArrayList<Option>(optionSetSize);
	}
	
	// Getters
	// generates the unique key for an Automobile object
	public String createAutoKey() {
		return make+model+year;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getYear() {
		 return year;
	 }
	
	public String getOptionSetName(OptionSet optionSet) {
		return optionSet.getName();
	}
	
	 public ArrayList<Option> getUserChoices() {
		 return userChoices;
	 }
	
	public OptionSet getOptionSet(int index) {		// GetOptionSet(by index value)
		if(optionSets == null || index > optionSets.size() - 1) {
			return null;
		}

		return optionSets.get(index);
	}
	
	public ArrayList<OptionSet> getOptionSets() {
		return optionSets;
	}
	
	public OptionSet getOptionSet(String name) {		// GetOptionSet(by name)
		for(OptionSet opSet : optionSets ) {
			if(opSet != null && opSet.getName().equalsIgnoreCase(name)) {
				return opSet;
			}
		}
		
		return null;
	}
	
	public void listOptions() {
		int number = 1;
		for(OptionSet optionSet : optionSets) {
			System.out.println("OptionSet:" + optionSet);
			ArrayList<Option> options = optionSet.getOptions();
			for(Option option : options) {
				System.out.println(number +":" + option);
				number++;
			}
			number = 1;
		}
	}
	public int getTotalPrice() {
		int totalPrice = 0;
		
		for(Option option : userChoices) {
			totalPrice += option.getPrice();
		}
		
		return totalPrice;
	}
	
	public String getOptionChoice(String optionSetName) {
		OptionSet optionSet = this.findOptionSetByName(optionSetName);
		if (optionSet.getOptionChoice() == null) {
			return null;
		}
		return optionSet.getOptionChoice().getName();
	}
	
	public int getOptionChoicePrice(String optionSetName) {
		OptionSet optionSet = this.findOptionSetByName(optionSetName);
		return optionSet.getOptionChoice().getPrice();
	}
	
	// Find
	// Find OptionSet with name
	public OptionSet findOptionSetByName(String optionSetName) {
		if (optionSets == null) {
			return null;
		}
		
		for (OptionSet opset : optionSets) {
			if(opset != null && opset.getName().equalsIgnoreCase(optionSetName)) {
				return opset;
			}
		}
		
		return null;
	}
	
	public int findOptionSetIndexByName(String optionSetName) {
		int i = 0;
		
		if (optionSets == null) {
			return -1;
		}
		
		for (OptionSet opset : optionSets) {
			if(opset != null && opset.getName().equalsIgnoreCase(optionSetName)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	// Setters
	public void setMake(String make) {
		this.make = make;
	} 
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setOptionSets(ArrayList<OptionSet> optionSets) {
		this.optionSets = optionSets;
	}
	
	public void setOptionSet(OptionSet optionSet, int index) {
		optionSets.add(index, optionSet);
	}
	
	public void addOption(String optionSetName, int index, String optionName, int optionPrice) {
		//find the optionSet with name
		for(OptionSet optionSet : optionSets) {
			if(optionSet != null && optionSet.getName().equalsIgnoreCase(optionSetName)) {
				optionSet.addOption(optionName, optionPrice, index); // create on option in the specific index
			}
		}		
	}
	
	public void setOptionSetValues(int index,  String name, String[] optionNames, int[] optionPrices) {
		OptionSet newOptionSet = new OptionSet(name, optionNames.length);
		
		for (int i=0; i < optionNames.length; i++) {
			newOptionSet.addOption(optionNames[i], optionPrices[i], i);
		}
	
		optionSets.add(index, newOptionSet);
	}
	
	public void setOptionChoice(String optionSetName, String optionName) {
		for (OptionSet optionSet : optionSets) {
			if (optionSet != null && optionSet.getName().equalsIgnoreCase(optionSetName)) {
				// set the Option named optionName in this OptionSet
				optionSet.setOptionChoice(optionName);
				// link the userChoice arraylist to choice
				userChoices.add(optionSet.choice);
			}
		}
	}
	
	// Delete
	public void deleteOptionSet(String optionSetName) {
		if (optionSets == null) {
			return;
		}
		// delete its chosem option from userChoice list
		deleteOptionChoice(optionSetName);
		// delete the OptionSet from optionSets list
		for(OptionSet opSet : optionSets) {
			if(opSet.getName().equalsIgnoreCase(optionSetName)) {
				optionSets.remove(opSet);
				return;
			}	
		}	
	}
	
	// Deletes teh related choice for the specific optionSet
	public void deleteOptionChoice(String optionSetName) {
		OptionSet willBeDeleted = new OptionSet(userChoices.size());

		// find the OptionSet that is gonna be deleted
		for (OptionSet optionSet : optionSets) {
			if (optionSet != null && optionSet.getName().equalsIgnoreCase(optionSetName)) {
				willBeDeleted = optionSet;
			}
		}
		for (Option option : userChoices) {
			if (option.equals(willBeDeleted.choice)) {
				userChoices.remove(option);
				return;
			}
		}
	}
	// Update
	public void updateOptionSet(String name, String[] optionNames, int[] optionPrices) {
		int index = findOptionSetIndexByName(name);
		
		if (index == -1) { // the OptionSet was not find
			return;
		}
		
		setOptionSetValues(index, name, optionNames, optionPrices);
	}
	
	public boolean updateOptionSetName(String oldName, String newName) {
		for(OptionSet optionSet : optionSets) {
			if(optionSet.getName().equalsIgnoreCase(oldName)) {
				optionSet.setName(newName);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateOptionPrice(String optionSetName, String optionName, int newPrice) {
		for(OptionSet optionSet : optionSets) {
			if(optionSet.getName().equalsIgnoreCase(optionSetName)) {
				optionSet.updateOptionPrice(optionName, newPrice);
				return true;
			}
		}
		return false;
	}
	// toString
	public void print() {
		System.out.println(this.toString());
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		String LINE_BREAK = System.getProperty("line.separator"); // \n
		String TAB = "\t";
		String className = this.getClass().getName();
		
		str.append(className).append(" Object {").append(LINE_BREAK)
			.append(TAB).append("make=").append(make).append(LINE_BREAK)
			.append(TAB).append("model=").append(model).append(LINE_BREAK)
			.append(TAB).append("year=").append(year)
			.append(LINE_BREAK).append(TAB).append("optionsets=[").append(LINE_BREAK);
		
		if(optionSets != null) {
			for (OptionSet optionset : optionSets) {
				if(optionset != null){
					str.append(optionset.toString()).append(LINE_BREAK);
				}
			}
		}
		
		str.append("]")
		.append(LINE_BREAK)
		.append("}");

		return str.toString();	
	}	
}