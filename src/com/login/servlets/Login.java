package com.login.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/springjdbc";
		String user1 = "root";
		String dbpsw = "NAVAL380";

		String sql = "select * from register where user=? and password=?";
		HttpSession session= request.getSession();
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		try{
		Class.forName(driverName);
		con = DriverManager.getConnection(url, user1, dbpsw);
		ps = con.prepareStatement(sql);
		ps.setString(1, user);
		ps.setString(2, password);
		rs = ps.executeQuery();
		if(rs.next())
		{ 
		user = rs.getString("user");
		password = rs.getString("password");
		if(user.equals(user) && password.equals(password))
		{
			
		session.setAttribute("user",user);
		session.setAttribute("password", password); 
		response.sendRedirect("welcome.jsp"); 
		} 
		}
		else
		response.sendRedirect("error.jsp");
		rs.close();
		ps.close(); 
		}
		catch(ClassNotFoundException | SQLException sqe)
		{
		System.out.println(sqe);
		} 
		}
		

}

