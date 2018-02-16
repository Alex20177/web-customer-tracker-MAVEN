package com.testDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDBConnection
 */
public class TestDBConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user="springstudent";
		String pass="springstudent1";
		String url="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String drive="com.mysql.jdbc.Driver";
		PrintWriter out = response.getWriter();
		
		try {
			
			out.println("Connection to "+url);
			
			Class.forName(drive);
			
			Connection connection = DriverManager.getConnection(url,user,pass);
			
			out.println("\n\nCONECTION SUCCESS!!");
			
			connection.close();
			
		}//Close try 
		
		catch (Exception e) {
			out.println("No success conection");
			out.println(e.getMessage());
		}//Close catch
		
	}//Close doGet method.

}//Close TestDBConnection
