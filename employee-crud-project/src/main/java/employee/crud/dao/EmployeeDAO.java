package employee.crud.dao;

import java.util.List;

import employee.crud.bean.Employee;

public interface EmployeeDAO {
	//1 - Insert Employee
	public boolean addEmployee(Employee employee);
	
	//2 - Update Employee
	public boolean updateEmployee(Employee employee);

	//3 - Delete Employee
	public boolean deleteEmployee(int employeeId);

	//4 - Get All Employees
	public List<Employee> getAllEmployees();

	//5 - Get One Employee
	public Employee getEmployee(int employeeId);

}
