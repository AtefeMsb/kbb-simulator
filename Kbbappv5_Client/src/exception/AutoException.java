/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package exception;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import adapter.FixAuto;
import model.Option;

public class AutoException extends Exception implements FixAuto {
	private int errorno;
	private String errormsg;
	private static String[] exceptionList = new String[100];
	
	// Constructor
	public AutoException(int errorno, String errormsg) {
		super();
		readFile("exceptions.txt");
		this.errorno = errorno;
		this.errormsg = errormsg;
		print();
		
	}
	
	public AutoException(int errorno) {
		super();
		readFile("exceptions.txt");
		this.errorno = errorno;
		this.errormsg = exceptionList[errorno];
		print();
		
	}
	
	public AutoException(String errormsg) {
		super();
		readFile("exceptions.txt");
		this.errormsg = errormsg;
		print();
	}
	
	// file reader
	public void readFile(String fileName) {
		boolean eof = false;
		int i = 1;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader buffer = new BufferedReader(fileReader);
			while(!eof) {
				String line = buffer.readLine();
				if(line == null) {
					eof = true;
				} else {
				String[] lineArray = line.split(",");
				exceptionList[i] = lineArray[1];
				i++;
				}
			}
			buffer.close();
		} catch(IOException ioe) {
			System.out.println("exception's file was not found");
		}
		
	}
	
	// Getter & Setter
	public int getErrorno() {
		return errorno;
	}
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public void print() {
		System.out.println("AutoException [errorno=" + errorno + ", errormsg=" + errormsg + "]");
	}
	
	// this methos will write an exception information in a text file
	private void log(String fileName) {
		
		try (
			BufferedWriter buffer = new BufferedWriter( new FileWriter(fileName));
		){ 
			Date date = new Date();
			buffer.write(this.errorno + ": " + exceptionList[this.errorno] + " ---> " + date.toString());
			buffer.newLine();
			
		} catch (IOException e) {
			System.out.println("Problem logging the exception!");
			e.printStackTrace();
		} 
	}
	
	public String fix(int errno) {
		Fix1to100 utilFixer = new Fix1to100();	// object to fix UTIL package exceptions
		
		log("log.txt");
		String answer = null;
		
		switch(errno) {
		
			case 1: 
				answer = utilFixer.fixMissingAutoName();
				break;

			case 2: 
				answer = utilFixer.fixMissingAutoPrice();
				break;
				
			case 3:
				answer = utilFixer.fixMissingOptionSetLength();
				break;
				
			case 4: 
				answer = utilFixer.fixMissingOption();
				break;
			
			case 5: 
				utilFixer.fixSerialization();
		
		}
		return answer;
		
	}
}