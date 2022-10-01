

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

@WebServlet("/UpdateRecord")
public class UpdateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pswd");
		String email=request.getParameter("mail");
		String mbl=request.getParameter("mbl");
		String addrs=request.getParameter("ads");
		String contr=request.getParameter("country");
		
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","anand","anand");
			 
			PreparedStatement ps=con.prepareStatement("Update employee set password=? , email=? , mobile_no=? , address=? , country=? where name=? ");
			 
			 ps.setString(1,pwd);
			 ps.setString(2,email);
			 ps.setString(3,mbl);
			 ps.setString(4, addrs);
			 ps.setString(5,contr);
			 ps.setString(6,uname);
			int i= ps.executeUpdate();
			
			out.print("<h1>"+i+" Record is Updated"+"</h1>");
			
			
			con.close();
			
		}
		catch(Exception c)
		{
			System.out.println("Driver not loaded");
		}
	}

}
