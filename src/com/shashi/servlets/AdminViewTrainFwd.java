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
						
						//List of candidates
						RequestDispatcher rd1 = req.getRequestDispatcher("ShowTab.jsp");
						rd1.include(req, res);
						
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
