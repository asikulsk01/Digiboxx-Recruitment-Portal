package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class AdminViewLinkFwd extends HttpServlet{

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from register where uname=?");
					ps.setLong(1,Long.parseLong(req.getParameter("uname")));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Selected Candidate Details</p1></div>");
						pw.println("<div class='tab'>"
								+ "<table>"
								+ "<tr><td class='blue'>username :</td><td>"+rs.getString("uname")+"</td></tr>"
								+ "<tr><td class='blue'>First name :</td><td>"+rs.getLong("fname")+"</td></tr>"
								+ "<tr><td class='blue'>Last Name :</td><td>"+rs.getString("lname")+"</td></tr>"
								+ "<tr><td class='blue'>Age :</td><td>"+rs.getString("addr")+"</td></tr>"
								+ "<tr><td class='blue'>Phone:</td><td>"+rs.getLong("phno")+"</td></tr>"
								+ "<tr><td class='blue'>Email :</td><td>"+rs.getLong("mailid")+" RS</td></tr>"
								+ "</table>"
								+ "</div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrains.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Train No."+req.getParameter("uname")+" is Not Available !</p1></div>");
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
