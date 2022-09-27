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
		Employee employee;
		
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
		for (Employee em: list) {
			employee = em;
			calcGrossPay(employee);
		}
		
	}
	
	public double calcGrossPay(Employee e) {
		double hours = e.getMaxHours();
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
					double h = (hours - 40);
					earnings = (earnings) + (pay * h);
					return earnings;
				} else if (hours > 60) {
					earnings = 0;
				}
				
			} else if (type == 'C') {
				
			}
		return earnings;
	}
	
//	private Employee searchType(char type) {
//		Employee em = null;
//		
//		for (Employee e: list) {
//			if (e.getType() == type) {
//				em = e;
//				break;
//			}
//		}
//		return em;
//	}

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
