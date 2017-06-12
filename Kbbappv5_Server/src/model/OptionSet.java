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
import java.util.Iterator;

public class OptionSet implements Serializable{
	
	String name;
	Option choice;			// the Option user picked in this OptionSet
	ArrayList<Option> options;
	
	// Constructors
	public OptionSet(int size) {
		this(null, size);
	}
	
	public OptionSet(String name, int size) {
		this.name = name;
		options = new ArrayList<Option>(size);
	}
	
	// Getters
	protected Option getOption(String name) {
		for (Option option : options) {
			if (option != null && option.getName().equalsIgnoreCase(name)) {
				return option;
			}
		}
		
		return null;
	}
	
	protected ArrayList<Option> getOptions() {
		return options;
	}

	protected String getName() {
		return name;
	}
	
	protected Option getOptionChoice() {
		return choice;
	}
	// Find
	protected Option findOption (String name) {
		for (Option option : options) {
			if (option.getName().equalsIgnoreCase(name)) {
				return option;
			}
		}
		
		return null;
	}
	
	protected ArrayList<Option> findOption(int price) {
		int j = 0;
		ArrayList<Option> samePriceOptions = new ArrayList<Option>();
		
		for (Option option : options) {
			if(option != null && option.getPrice() == price) {
				samePriceOptions.add(option);
				j++;
			}
		}
		
		if (j == 0) {
			return null;
		}
		
		return samePriceOptions;
	}
	
	// Setters
	protected void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setOptionChoice(String optionName) {
		this.choice = this.findOption(optionName);
	}
	
	protected void addOption(String name, int price, int index) {
		if (index < 0 || index > options.size() ) {
			return;
		}
		
		this.options.add(index, new Option(name, price));
	}
	
	protected void addOption(String name, int price) {
		this.options.add(new Option(name, price));
	}
	
	// Delete
	protected void deleteOption(String optionName) {
		for (Option option : options) {
			if (option != null && option.getName().equalsIgnoreCase(optionName)) {
				// remove the option from ArrayList of Options
				options.remove(option);
				// clear the choice variable, if it was chosen
				deleteOptionChoice(optionName);
				return;
			}
		}		
	}
	
	protected void deleteOption(int price) {	// can have more than one Option with the same price
		ArrayList<Option> deleteCandidates = new ArrayList<Option>();
		
		// Pass 1 - collect delete candidates
		for (Option option : options) {
			if (option != null && option.getPrice() == price) {
				deleteCandidates.add(option);
			}
		}
		
		// Pass 2 - delete
		for (Option deleteCandidate : deleteCandidates) {
			// clear the choice variable, if it was chosen
			deleteOptionChoice(deleteCandidate.getName());
			// remove option with specific price from Options arraylist
			options.remove(deleteCandidate);
		}


	}
	
	// clear the choice by optionName
	protected void deleteOptionChoice(String optionName) {
		if (choice.getName().equalsIgnoreCase(optionName)) {
			choice = null;
		}
	}
		
	// Update
	protected void updateOptionPrice(String name, int price) { // find by name
		for (Option option : options) {
			if (option != null && option.getName().equalsIgnoreCase(name)) {
				option.setPrice(price);
			}
		}	
	}
	
	protected void updateOptionName(int price, String name) {	// find by price - might be more than one
		for (Option option : options) {
			if (option != null && option.getPrice() == price) {
				option.setName(name);
			}
		}
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
			.append(TAB).append("options=[").append(LINE_BREAK);
		
		if(options != null) {
			for (Option option : options) {
				if(option != null){
					str.append(option.toString()).append(LINE_BREAK);
				}
			}
		}
		
		str.append("]")
		.append(LINE_BREAK)
		.append("}");

		return str.toString();
	}
}