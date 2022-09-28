package employee;

public class Employee implements Comparable<Employee> {
	
	private int empNo;
	private String empName;
	private String department;
	private char type;
	private double payRate;
	private double maxHours;
	
	/**
	 * Creates an Employee
	 */
	Employee() {
		empNo = 0;
		empName = null;
		department = null;
		type = 0;
		payRate = 0;
		maxHours = 0;
	}
	/**
	 * Creates an Employee
	 * @param number 
	 * @param name 
	 * @param dep 
	 * @param t 
	 * @param pay
	 * @param max
	 */
	Employee(int number, String name, String dep, char t, double pay, double max) {
		empNo = number;
		empName = name; 
		department = dep;
		type = t;
		payRate = pay;
		maxHours = max;
	}
	/**
	 * Creates an Employee
	 * @param Employee
	 */
	Employee(Employee e) {
		empNo = e.empNo;
		empName = e.empName; 
		department = e.department;
		type = e.type;
		payRate = e.payRate;
		maxHours = e.maxHours;
	}
	/**
	 * @return the empNo
	 */
	public int getEmpNo() {
		return empNo;
	}
	/**
	 * @param empNo the empNo to set
	 */
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the payRate
	 */
	public double getPayRate() {
		return payRate;
	}
	/**
	 * @param payRate the payRate to set
	 */
	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}
	/**
	 * @return the maxHours
	 */
	public double getMaxHours() {
		return maxHours;
	}
	/**
	 * @param maxHours the maxHours to set
	 */
	public void setMaxHours(int maxHours) {
		this.maxHours = maxHours;
	}
	
	/**
	 * compareTo method to compare employees based on employee number
	 * @return result -1, 0, or 1 to declare if <, =, or >
	 */
	@Override
	public int compareTo(Employee o) {
		int result = 0;
		
		if (this.empNo < o.getEmpNo()) {
			result = -1;
		}
		else if (this.empNo > o.getEmpNo()) {
			result = 1;
		}
		else {
			return 0;
		}
		return result;
	}
	
}
