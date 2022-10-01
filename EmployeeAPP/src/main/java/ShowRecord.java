

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ShowRecord")
public class ShowRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	           String uname=request.getParameter("name");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","anand","anand");
			 
			PreparedStatement ps=con.prepareStatement("Select * from employee where name=?");
			 
			 ps.setString(1,uname);
			 ResultSet rs= ps.executeQuery();
			 ResultSetMetaData rss=rs.getMetaData();
			 
			 int n=rss.getColumnCount();
			 
			out.print("<table border='1' >");
			 
			for (int i=1;i<=n;i++)
			{
				out.println("<td> <font color=blue size=3 >"+"<br>"+rss.getColumnName(i));
			}
			out.println("<tr>");
			
			while(rs.next())
			{
				for(int i=1;i<=n;i++)
				{
					out.println("<td><br>"+rs.getString(i));
				}
				out.println("<tr>");
			}
			
			con.close();
			
		}
		catch(Exception c)
		{
			System.out.println("Driver not loaded");
		}

	}

}
