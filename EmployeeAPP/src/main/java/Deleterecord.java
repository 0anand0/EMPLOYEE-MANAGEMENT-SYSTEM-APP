

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


@WebServlet("/Deleterecord")
public class Deleterecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		   response.setContentType("text/html");
		   PrintWriter out=response.getWriter();
		   
		  String uname=request.getParameter("uname");
		  
		  try
		  {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","anand","anand");
			  
			  PreparedStatement ps=con.prepareStatement("Delete from employee where name=?");
			  
			  ps.setString(1,uname);
			  int i=ps.executeUpdate();
			  out.print("<h1>"+i+"  record is deleted"+"</h1>");
			   
			  con.close();
		  }
		  catch(Exception e)
		  {
			  System.out.println("Exptn");
		  }
	}

}
