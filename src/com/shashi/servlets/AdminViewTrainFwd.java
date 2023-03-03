package com.shashi.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;
@SuppressWarnings("serial")
public class AdminViewTrainFwd extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from register");
					ResultSet rs = ps.executeQuery();
					if(rs.next()) 
					{
						RequestDispatcher rd = req.getRequestDispatcher("ViewTrains.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Candidates List	</p1></div>");
						pw.println("<div class='tab'><table><th>Username</th><th>First name</th><th>Last name</th>"
								+ "<th>Age</th><th>Phone no</th><th>Email</th>");
						//System.out.println("Testing... ");
						String uname;
						String fName;
						String lName;
						String age;
						String phone;
						String email;
						do {
							 uname = rs.getString("UNAME");
							 fName = rs.getString("FNAME");
							 lName = rs.getString("LNAME");
							 age = rs.getString("ADDR");
							 phone = rs.getString("PNO");
							 email = rs.getString("MAINID");
							 //System.out.println("Testing... ");
							 System.out.println(fName);
								pw.println(""
								+ "<tr> "
								+ "<td><a href='viewadmin?trainNo="+uname+"'>"+uname+"</a></td>"
								+ "<td>"+fName+"</td>"
								+ "<td>"+lName+"</td>"
								+ "<td>"+age+"</td>"
								+ "<td>"+phone+"</td>"
								+ "<td>"+email+" RS</td></tr>");
							}while(rs.next());
						pw.println("</table></div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("ViewTrains.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu red'> No Candidates</p1></div>");
					}
				}
				catch(Exception e) {}
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
