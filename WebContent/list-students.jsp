<%@ page import="mo.essam.models.Student"%>
<%@ page import="java.util.List"%>
<%@ page import="mo.essam.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<link type="text/css" rel="stylesheet" href="CSS/style.css">

<title>Student Web App</title>
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>Ain Shams University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">


			<input type="button" value="Add Student"
				onclick="window.location.href='Add-Student-Form.jsp'; return false;"
				class="add-student-button" />

			<table>

				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>


				<c:forEach var="item" items="${Students}">

					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="StudentID" value="${item.id }" />
					</c:url>
					
					<tr>
						<td>${item.firstName }</td>
						<td>${item.lastName }</td>
						<td>${item.email }</td>
						<td><a href="${tempLink}">UPDATE</a></td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
</body>


</html>