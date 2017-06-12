/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package adapter;

import java.util.Properties;

public interface CreateAuto {
	
	public abstract void buildAuto(String fileName, int fileType);
	public abstract void printAuto(String modelName);
//	public abstract void builAutoFromPropertiesObject(Properties properties);

}
