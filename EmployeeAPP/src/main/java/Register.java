

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pswd");
		String email=request.getParameter("mail");
		String gender=request.getParameter("gen");
		String mbl=request.getParameter("mbl");
		String addrs=request.getParameter("ads");
		String contr=request.getParameter("country");
		
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","anand","anand");
			 
			PreparedStatement ps=con.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");
			 ps.setString(1,uname);
			 ps.setString(2,pwd);
			 ps.setString(3,email);
			 ps.setString(4,gender);
			 ps.setString(5,mbl);
			 ps.setString(6, addrs);
			 ps.setString(7, contr);
			 
			int i= ps.executeUpdate();
			
			 if(i==1)
		      {
				 response.sendRedirect("regsucs.html");
		      }
		      else
		      {
		    	  response.sendRedirect("login.html");
		      }
		
			
			
			con.close();
			
		}
		catch(Exception c)
		{
			System.out.println("Driver not loaded");
		}
	}

}
