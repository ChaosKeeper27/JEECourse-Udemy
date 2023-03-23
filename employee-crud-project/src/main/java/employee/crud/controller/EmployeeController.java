package employee.crud.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.crud.bean.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.db.EmployeeDAOImpl;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDAO employeeDAO = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	
    	employeeDAO = new EmployeeDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("EmployeeController, doPost method started");
		String action = request.getServletPath();
		System.out.println("doPost, actions ==> " + action);
		
		switch (action) {
		case "/add": {
			addNewEmployee(request, response);
			break;
		}
		case "/update": {
			updateEmployee(request, response);
			break;
		}
		case "/delete": {
			deleteEmployee(request, response);
			break;
		}
		case "/list": {
			getAllEmployees(request, response);
			break;
		}
		case "/get": {
			getEmployee(request, response);
			break;
		}

		default:
			getAllEmployees(request, response);
			break;
		}		
	}

	private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Start getEmployee");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("getEmployee, Employee ID ==> " + id);
		
		Employee employee = employeeDAO.getEmployee(id);
		System.out.println("getEmployee, result is ==> " + employee);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Start getAllEmployees");
		

		List<Employee> employees = employeeDAO.getAllEmployees();
		System.out.println("getAllEmployees, number of employees ==> " + employees.size());
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
			request.setAttribute("employee", employees);
			dispatcher.forward(request, response);
			System.out.println(request.getAttribute("employee"));
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Start deleteEmployee");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("deleteEmployee, Employee ID ==> " + id);
		
		boolean result = employeeDAO.deleteEmployee(id);
		System.out.println("deleteEmployee, result is ==> " + result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Start updateEmployee");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Employee employee = new Employee(id, name, email, phone, address);
		System.out.println("updateEmployee, Employee details ==> " + employee);
		
		boolean result = employeeDAO.updateEmployee(employee);
		System.out.println("updateEmployee, result is ==> " + result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Start addNewEmployee");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Employee employee = new Employee(name, email, phone, address);
		System.out.println("addNewEmployee, Employee details ==> " + employee);
		
		boolean result = employeeDAO.addEmployee(employee);
		System.out.println("addNewEmployee, result is ==> " + result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
