/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;
import util.FileIO;

public class BuildCarModelOptions implements AutoServer{
	
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	DefaultSocketClient dsc = null;

	private BuildAuto buildAuto;

	/**
	 * Constructor
	 */
	public BuildCarModelOptions(){
		buildAuto = new BuildAuto();
		try {
			serverSocket = new ServerSocket(3000);
			System.out.println("server is up and waiting for connections...");
			while(true){
				Socket socket = serverSocket.accept();
				dsc = new DefaultSocketClient(socket, this);
				new Thread(dsc).start();
				System.out.println("Server accepted the client");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BuildAuto getBuildAuto() {
		return buildAuto;
	}

	public void setBuildAuto(BuildAuto buildAuto) {
		this.buildAuto = buildAuto;
	}

		
	@Override
	public void builAutoFromPropertiesObject(Properties properties) {
		System.out.println("testing");
		buildAuto.builAutoFromPropertiesObject(properties);	
	}

	@Override
	public String showList() {
		return buildAuto.showList();
	}

	@Override
	public Automobile findAutomobileInLHM(String autoName) {
		Automobile auto = buildAuto.findAutomobileInLHM(autoName);
		return auto; 
	}

	public static void main(String args[]) {
		System.out.println("start in server...");
		BuildCarModelOptions server = new BuildCarModelOptions();
	}
}
