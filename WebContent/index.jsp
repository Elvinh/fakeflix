<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*"
%>

<html>
<head>
<style>
body {
	text-align: center;
	background-color: black;
	color: white; 
}
li {
	color: red;
}
table.center {
	text-align: center;
    margin-left:auto; 
    margin-right:auto;
}
</style>
</head>
	<h3>hello world</h3><br>
	<body>
		<%
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection =
		      DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "e951l632v");
		    Statement select = connection.createStatement();
		    ResultSet result = select.executeQuery("Select *  from stars ");
		    out.println("The results of the query");
		
		    ResultSetMetaData  metadata;
		    metadata = result.getMetaData();
		    out.println("There are "+metadata.getColumnCount()+" columns <br>");
		    for (int i=1; i<=metadata.getColumnCount(); i++){
		      out.println("Type of column "+i+" is " +
		                  metadata.getColumnTypeName(i) + "<br>");
		    }
		   %>
			
		<table class="center" border="1">
		    <% while (result.next()) {%>
		      <tr>
		      	<td> <% out.print("First_name  = "+result.getString(2)); %> </td>
		      	<td> <% out.print("  Last_name = "+result.getString(3)); %> </td>
		      	<td> <% out.println("<br>"); %> </td>
		      </tr>
		   <% } %>
		</table>

	</body>
</html>
