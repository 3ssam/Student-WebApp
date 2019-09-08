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

			<table>

				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>


				<c:forEach var="item" items="${Students}">

					<tr>
						<td>${item.firstName }</td>
						<td>${item.lastName }</td>
						<td>${item.email }</td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
</body>


</html>