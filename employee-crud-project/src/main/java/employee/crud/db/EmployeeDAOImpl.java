package employee.crud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import employee.crud.bean.Employee;
import employee.crud.dao.EmployeeDAO;

public class EmployeeDAOImpl implements EmployeeDAO{

	private static Connection connection = DBConnection.connection;
	
	@Override
	public boolean addEmployee(Employee employee) {
		System.out.println("Start addEmployee");
		
		try {
			String insertStatement = "INSERT INTO employee (name, email, phone, address) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());
			
			int result = preparedStatement.executeUpdate();
			
			return result == 1 ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		System.out.println("Start updateEmployee");
		
		try {
			String updateStatement = "UPDATE employee "
					+ "SET NAME = ?, email = ?, phone = ?, address = ? "
					+ "WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setInt(5, employee.getId());
			
			int result = preparedStatement.executeUpdate();
			
			return result == 1 ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		System.out.println("Start deleteEmployee");
		
		try {
			String deleteStatement = "DELETE FROM employee "
					+ "WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, employeeId);
			
			int result = preparedStatement.executeUpdate();
			
			return result == 1 ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("Start getAllEmployees");
		
		try {
			String getAllEmployeesStatement = "SELECT * FROM employee";
			PreparedStatement preparedStatement = connection.prepareStatement(getAllEmployeesStatement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			
			while(resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));
				
				employees.add(employee);
			}
			
			return employees;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Employee getEmployee(int employeeId) {
		System.out.println("Start getEmployee");
		
		try {
			String getEmployeeStatement = "SELECT * FROM employee "
					+ "WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(getEmployeeStatement);
			preparedStatement.setInt(1, employeeId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			Employee employee = new Employee();
			
			while(resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));				
			}
			
			return employee;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public static void main(String[] args) {
		
		//create
		/*
		 * Employee employee = new Employee(); 
		 * employee.setName("Percy");
		 * employee.setEmail("madscientist@whitestone.com");
		 * employee.setPhone("18005651775"); 
		 * employee.setAddress("White Stone Castle");
		 * 
		 * EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		 * System.out.println(employeeDAOImpl.addEmployee(employee));
		 */
		
		//update
		/*
		 * Employee employee = new Employee(); 
		 * employee.setId(8);
		 * employee.setName("Kelith"); 
		 * employee.setEmail("goldfish@whitestone.com");
		 * employee.setPhone("561864564"); 
		 * employee.setAddress("Air Ashari");
		 * 
		 * EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		 * System.out.println(employeeDAOImpl.updateEmployee(employee));
		 */
		
		//delete
		/*
		 * EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		 * System.out.println(employeeDAOImpl.deleteEmployee(5));
		 */
		
		//get all employees
		/*
		 * EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		 * System.out.println(employeeDAOImpl.getAllEmployees().size());
		 */
		
		//get one employee
		EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		System.out.println(employeeDAOImpl.getEmployee(10));


	}

}
