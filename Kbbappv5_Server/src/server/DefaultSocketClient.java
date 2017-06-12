/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package server;

import java.io.*;
import java.net.*;
import model.Automobile;
import java.util.Properties;

public class DefaultSocketClient extends Thread
								 implements SocketClientInterface,
										    SocketClientConstants {

    private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;
    private Socket sock;
    private String strHost;
    
    private static final int WAITING = 0;
    private static final int UPLOAD_PROPERTY_FILE = 1;
    private static final int LIST_OF_CAR_MODELS = 2;
    private static final int GET_AUTO = 3;
    

    private int state = WAITING;
    
    private BuildCarModelOptions buildCarModelOptions;

    // constructor
    public DefaultSocketClient(Socket socket, BuildCarModelOptions buildCarModelOptions) {       
		setSock (socket);
		this.buildCarModelOptions = buildCarModelOptions;
	}
    
    private void setSock(Socket socket) {
		this.sock = socket;
		
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
 	     outputStream = new ObjectOutputStream(sock.getOutputStream());
 	     inputStream = new ObjectInputStream(sock.getInputStream());
 	   } catch (Exception e){
 	     if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
 	     return false;
 	  }
 	  return true;
 	}
    
    
    private void processClientRequest(Object userMessage) {
       	Properties propsObj = null;
       	
       	System.out.println("Server state: " + state);
       	
       
		switch(state) {
		case WAITING:
			String selectedOption = (String) userMessage;

			System.out.println("Server received user selection: " + selectedOption);
			
			if (selectedOption.equals("1")) {
				
				state = UPLOAD_PROPERTY_FILE;
				
			} else if (selectedOption.equals("2")) {
			
				String modelsList = buildCarModelOptions.showList();
				
				try {
					outputStream.writeObject(modelsList);
					state = GET_AUTO;
				} catch (IOException e) {
					e.printStackTrace();
					state = WAITING;
				}
			}
			
			break;
			
		case UPLOAD_PROPERTY_FILE: // received properties file
			 try {
				propsObj = (Properties) userMessage;
				
				// builds auto from Properties file and add it to LHM
				buildCarModelOptions.builAutoFromPropertiesObject(propsObj);
				
				// send response to client for success
				outputStream.writeObject("success");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			state = WAITING;
			break;
		
		case GET_AUTO: 
			try {
				
				String selectedModel = (String) userMessage;
				Automobile selectedAuto = buildCarModelOptions.findAutomobileInLHM(selectedModel);
				
				outputStream.writeObject(selectedAuto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			state = WAITING;
			
		} // switch
    } // processClientRequest


    public void handleSession(){
    	Object userMessage = null;
    	try {
	    	while ((userMessage = inputStream.readObject()) != null) {
	           	if (userMessage != null) {
	           		processClientRequest(userMessage);
	    		}
	    	}
    	} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
    }       

    public void closeSession(){
       try {
    	   inputStream = null;
    	   outputStream = null;
           sock.close();
       }
       catch (IOException e){
         if (DEBUG) System.err.println("Error closing socket to ");
       }       
    }

    public void setHost(String strHost){
            this.strHost = strHost;
    }

}