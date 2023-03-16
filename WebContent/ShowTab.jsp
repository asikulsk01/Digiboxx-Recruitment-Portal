<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Candidate</title>
<style>*{
			box-sizing: border-box;
		}

.button:hover {opacity: 10}
		a:hover{
		color:#FE0505  ;	}
	a:link{
		color: green;
		text-decoration: none;
	}
	.tab{
		border:1px black solid;
		border-radius: 10px;
		background-color:#E6E319 ;
		margin-left: 30%;
		width:680px;
		color:black;
		font-weight: bold;
		font-style:normal;
		text-align:center;
		font-size: 20px;
		margin-bottom:10px;
		padding:10px;
		margin-top:10px;
	}

  
	table{
		border:2px solid black;
		text-align: center;
		padding:5px;
		cellspacing:2px;
		width:680px;
	}
	tr:hover {
		background-color: #f5f5f5;
		color:black;
	}
	td{
		padding:10px;
		border:1px solid black;
	}
	th{
		color:green;
		font-size:20px;
	}
	</style>
</head>
<body>
<%
try
{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","RESERVATION","manager");
	if(con==null)
	{
		%>
		Connection not created!
<%}
	else
	{
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM REGISTER");
		ResultSetMetaData rsm=rs.getMetaData();
		int col=rsm.getColumnCount();
		%>
		
		<table class='tab' border=9>
		
		<%
		for(int i=1;i<=col;i++)
		{
		%>
		<th><%=rsm.getColumnName(i) %></th>
		<%
		}
		while(rs.next())
		{%>
		<tr>
		<%
		for(int i=1;i<=col;i++)
		{%>
			<td><%=rs.getString(i) %></td>
		<%
		}%>
		</tr>
		<%}
		%>
		</table>
	<%}
	
}
catch(Exception e){out.println(e);}
%>
</div>
</body>
</html>