/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package server;

import java.util.Properties;

import model.Automobile;

public interface AutoServer {
	
	public abstract void builAutoFromPropertiesObject(Properties properties);
	public abstract String showList();
	public abstract Automobile findAutomobileInLHM(String autoName);

}

