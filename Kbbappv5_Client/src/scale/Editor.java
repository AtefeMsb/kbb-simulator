/**
 * Name: Atefe Mosayebi
 * Class and Section: 35B - Mon/Wed 8-10
 * Assignment Number: lab 5
 * Due Date: : 06/10/2017
 * Date Submitted: 06/10/2017
 */
package scale;

public class Editor implements Runnable {
	EditOptions editOptions;
	String[] info;
	int threadno;
	
	public Editor(int threadno, EditOptions editOptions, String[] info) {
		this.editOptions = editOptions;
		this.threadno = threadno;
		this.info = info;
	}
	
	@Override
	public void run() {
		switch (threadno) {
		case 0:
			System.out.println("Start thread " + threadno + " --> updateOptionPrice");
			break;
		case 1:
			System.out.println("Start thread " + threadno + " --> updateOptionSetName");
			break;
		}
		operations();
		System.out.println("Stopping thread " + threadno);	
	}
	
	public void operations() {
		switch (threadno) {
		case 0:
			editOptions.updateOptionPrice(info);
			break;
		case 1:
			editOptions.updateOptionSetName(info);
			break;
		}
	}
}