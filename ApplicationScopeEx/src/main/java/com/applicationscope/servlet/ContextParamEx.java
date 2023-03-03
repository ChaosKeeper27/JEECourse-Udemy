package com.applicationscope.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class ContextParamEx
 */
public class ContextParamEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		config.getServletContext();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContextParamEx() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();
		
		ServletContext servletContext = request.getServletContext();
		
		printWriter.print("Context parameters");
		printWriter.print("DB param: " + servletContext.getInitParameter("db-ip"));
		printWriter.print("DB param: " + servletContext.getInitParameter("db-port"));
		
		Enumeration<String> enumeration = servletContext.getInitParameterNames();
		printWriter.print(enumeration);
		while (enumeration.hasMoreElements()) {
			String name = (String) enumeration.nextElement();
			printWriter.print(servletContext.getInitParameter(name) + ", ");
			
		}

		
//		request.getServletContext(); //1
//		getServletContext(); //2
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
