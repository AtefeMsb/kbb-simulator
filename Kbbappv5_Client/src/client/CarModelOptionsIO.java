/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;


public class CarModelOptionsIO {
	
	private Socket socket = null;
	DefaultSocketClient dsc = null;
	
	public CarModelOptionsIO() {
		try {
			socket = new Socket("localhost", 3000);
			dsc = new DefaultSocketClient(socket);
			new Thread(dsc).start();
			System.out.println("client is running");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * print the menu
	 * @return String
	 */
	public static String printMenu() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter one of the following commands:");
		System.out.println("1 - Upload Properties File");
		System.out.println("2 - Configure a Car");
		System.out.println("3 - Exit");
		System.out.println();
		
		String choice = null;
		while(choice == null) {
			System.out.println("Enter \"1\", \"2\" or \"3\"");
			try {
				choice = reader.readLine();
				if (choice.equals("3")  && choice.equals("2") && choice.equals("1")) {
					choice = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return choice;
	}
	
	/**
	 * ask for URL and create properties object using the load method
	 * @return Properties object
	 */
	public static Properties createPropertiesObject() {
		System.out.println("Enter a properties file path:");
		String filePath = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			// read url from the user
			filePath = in.readLine(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		FileInputStream input = null;
		
		try {
			input = new FileInputStream(filePath);
			// loads the entire file in the properties object
			properties.load(input);
		} catch(FileNotFoundException ex) {
			System.out.println("Couldnt open the properties file");
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return properties;	
	}

	
	public static void main(String args[]) {
		CarModelOptionsIO client = new CarModelOptionsIO();
	}

}
