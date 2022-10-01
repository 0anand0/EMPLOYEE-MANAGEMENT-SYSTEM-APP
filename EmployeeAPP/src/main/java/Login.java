

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String psd=request.getParameter("pwd");
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","anand","anand");
			
			PreparedStatement ps=con.prepareStatement("Select * from employee where name=? and password=?");
			
		       ps.setString(1,uname);
		       ps.setString(2,psd);
		       
		       ResultSet rs=ps.executeQuery();
		       
		       if(rs.next())
		       {
		    	   response.sendRedirect("userhome.html");
		       }
		       
		       else
		       {
		    	   out.println(" Please ...Enter valid username or password");
		       }
		       con.close();
		}
		catch(Exception e)
		{
			System.out.println("EX");
		}
	}

}
