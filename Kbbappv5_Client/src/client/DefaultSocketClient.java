/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package client;

import java.net.*;
import java.io.*;
import util.FileIO;
import model.Automobile;
import java.util.Properties;

public class DefaultSocketClient extends Thread
								 implements SocketClientInterface,
										    SocketClientConstants {

    private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;
    private Socket sock;
    private String strHost;
    private int iPort;
    
    // constructor   
    public DefaultSocketClient(Socket socket) {       
    	sock = socket;
    }
    
    // run
    public void run(){
       if (openConnection()){
          handleSession();
          //closeSession();
       }
    }

    public boolean openConnection(){
	   try {
		   
		 // sends data to server
	     outputStream = new ObjectOutputStream(sock.getOutputStream());
	     
	     // receives from server
	     inputStream = new ObjectInputStream(sock.getInputStream());
	   } catch (Exception e){
	     if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
	     return false;
	  }
	  return true;
	}
    
    // when connection get started handle the session - being called once
    public void handleSession(){
    	// get a choice from the user
    	String choice = CarModelOptionsIO.printMenu();
    	
    	// based on user choice send data to server
    	boolean keepAlive = createServerRequst(choice);
		// listen to server response  & if the connection should alive
		while (keepAlive) {
			// get a choice from the user
			choice = CarModelOptionsIO.printMenu();
			
			// based on user choice send data to server
			keepAlive = createServerRequst(choice);
		}
		System.out.println("Closing Session");
		closeSession();

	}

    public boolean createServerRequst(String choice) {
    	try {	
	    	switch(choice){
	        case "1":
	        	outputStream.writeObject(choice);
	        	Properties props = CarModelOptionsIO.createPropertiesObject();
	        	outputStream.writeObject(props);
	        	String status = (String) inputStream.readObject();
				System.out.println("Status for choice 1: " + status);
	        	break;
	        	
	        case "2":
	        	outputStream.writeObject(choice);
	        	String autoModels = null;
				try {
					autoModels = (String) inputStream.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	        	SelectCarOption selectCarOption = new SelectCarOption(autoModels);
	        	String userChoice = selectCarOption.showModelsAndSelect();
	        	outputStream.writeObject(userChoice);
	        	
	        	// receive the selected automobile from server
	        	Automobile automobile = null;
				try {
					automobile = (Automobile)inputStream.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	        	
	        	// configure and display the automobile
	        	selectCarOption.configureCar(automobile);
	        	selectCarOption.displayconfiguredCar(automobile);
	
	        	break;
	        case "3":
	        	return false;
	        					
			default:
				System.out.println("Enter \"1\", \"2\" or \"3\"");
				break;
			}
		} catch (SocketException se) {
			se.printStackTrace();
			// System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	return true;
    }

    public void closeSession(){
       try {
    	   inputStream = null;
    	   outputStream = null;
          sock.close();
       }
       catch (IOException e){
         if (DEBUG) System.err.println("Error closing socket to " + strHost);
       }       
    }
}