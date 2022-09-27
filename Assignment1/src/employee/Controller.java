package employee;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {

	private final String FILE_NAME = "res/EmployeeInfo.txt";
	private File eFile;
	private ArrayList <Employee> list;
	
	public Controller() {
		
		list = new ArrayList<>();
		Employee employee = null;
		
		//make new file with the file name
		eFile = new File(FILE_NAME);
		
		//check if it exists, else create a new text file
		if(eFile.exists()) {
			//call method to load arrayList
			loadData();
		} else {
			try {
				eFile.createNewFile();
			} catch (IOException e) {
				System.out.print("Error creating new file.");
				e.printStackTrace();
			}
			loadData();
		}
		double gross;
		for (Employee em: list) {
			employee = em;
			double hours = employee.getMaxHours();
			gross = calcGrossPay(hours, employee);
		}
//		deductions(gross);
	}


	public double calcGrossPay(double h, Employee e) {
		double hours = h;
		double earnings = 0;
		char type = e.getType();
		
			if (type == 'S') {
				if (hours <= 168) {
					earnings = e.getPayRate() / 52;
				}
				
			} else if (type == 'H') {
				double pay = e.getPayRate() / 24;
				earnings = pay * hours;
				if (hours > 40 && hours <= 60) {
					double hr = (hours - 40);
					earnings = (earnings) + (pay * hr);
				} else if (hours > 60) {
					earnings = 0;
				}
				
			} else if (type == 'C') {
				if (hours <= e.getMaxHours()) {
					earnings = e.getPayRate() * hours;
				} else {
					earnings = 0;
				}
			}
		return earnings;
	}
	
	public void deductions(double g) {
		double gross = g;
		calcWithHold(gross);
		
	}

	public void calcWithHold(double gross) {
		
	}
	public void calcCPP(double gross) {
		
	}

	public void calcEl(double gross) {
		
	}
	public void calcExtHealth(double gross) {
	
	}
	public void calcUnionDues(double gross) {
	
	}
	public void calcNetPay(double hours) {
		
	}
	
	public void loadData() {
		
		File file = new File(FILE_NAME);
		String currentLine;
		String[] splittedLine;
		
		if (file.exists()) {
			Scanner fileReader = null;
			try {
				fileReader = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while (fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splittedLine = currentLine.split(" ");
				Employee p = new Employee(Integer.parseInt(splittedLine[0]), splittedLine[1], splittedLine[2],
						splittedLine[3].charAt(0),Double.parseDouble(splittedLine[4]), Double.parseDouble(splittedLine[5]));
				list.add(p);
			}
			fileReader.close();
		}
	}
}
