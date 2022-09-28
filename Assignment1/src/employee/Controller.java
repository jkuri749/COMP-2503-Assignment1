package employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import employee.Employee;

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
		double gross = 0;
		for (Employee em: list) {
			employee = em;
			double hours = employee.getMaxHours();
			gross = calcGrossPay(hours, employee);
			System.out.println("gross: " + gross);
			deductions(gross,employee);
//			break;
		}

	}


	public double calcGrossPay(double h, Employee e) {
		double hours = h;
		double earnings = 0;
		char type = e.getType();
		final int weekPerYear = 52;
		final int regHourly = 40;
		final int maxHourly = 60;
		final int hoursPerDay = 24;
		final int regSalaryHours = 168;
		
			if (type == 'S') {
				if (hours <= regSalaryHours) {
					earnings = e.getPayRate() / weekPerYear;
				}
				
			} else if (type == 'H') {
				double pay = e.getPayRate() / hoursPerDay;
				earnings = pay * hours;
				if (hours > regHourly && hours <= maxHourly) {
					double hr = (hours - regHourly);
					earnings = (earnings) + (pay * hr);
				} else if (hours > maxHourly) {
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
	
	public void deductions(double g, Employee e) {
		double gross = g;
		char t = e.getType();
		
		calcWithHold(gross);
		calcCPP(gross);
		calcEl(gross);
		
		if (t == 'S' || t == 'H') {
			calcExtHealth(gross);
		}
		calcUnionDues(gross);
	}

	public double calcWithHold(double gross) {
		double g = gross;
		double deduct1 = 7.5;
		int deduct2 = 12;
		int deduct3 = 17;

		if (g < 1000) {
			g = g*deduct1;
		} else if (g >= 1000 && g < 2000) {
			g *= deduct2;
		} else if (g > 2000) {
			g *= deduct3;
		} else {
			System.out.print("Sorry, you are unemployed");
		}
	
		return g;
	}
	public double calcCPP(double gross) {
		double g = gross;
		double deduct1 = 7.5;
		g *= deduct1;
		return g;
	}

	public double calcEl(double gross) {
		double g = gross;
		double deduct1 = 1.8;
		g *= deduct1;
		return g;
	}
	public void calcExtHealth(double gross) {
		
	}
	public void calcUnionDues(double gross) {
	
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
	
	/**
	 * toString method to convert employee type from char to string
	 * @param employee
	 * @return employee type
	 */
	public String toString(Employee e) {
		String name = "Unkown";
		char c = e.getType();
		
		if (c == 'S' | c == 's') {
			name = "Salary";
		}
		else if (c == 'H' | c == 'h') {
			name = "Hourly";
		}
		else if (c == 'C' | c == 'c') {
			name = "Contractor";
		}
		return name;
			
	}
	
	/**
	 * print method to print employee details
	 * @param employee
	 */
	public void prinDetails(Employee e) {
		System.out.println("Employee Number: " + e.getEmpNo());
		System.out.println("Employee Name: " + e.getEmpName());
		System.out.println("Employee Department: " + e.getDepartment());
		System.out.println("Employee Type: " + toString(e));
		System.out.println("Pay Rate: " + e.getPayRate());
		System.out.println("Max Hours: " + e.getMaxHours());
	}
}
