<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Persons</title>
<style>
	.headers {
		background-color: orange;
		font:bold;
		border:thick;
	}
</style>
</head>
<body>

	<b>Persons List</b>
	<br>
	<table id="persons" border="1">
		<tr>
			<th class="headers">Username</th>
			<th class="headers">Firstname</th>
			<th class="headers">Lastname</th>
			<th class="headers">Age</th>
			<th class="headers">Zipcode</th>
		</tr>
		<c:forEach var="person" items="${persons}">
			<tr>
				<td>${person.username}</td>
				<td>${person.firstname}</td>
				<td>${person.lastname}</td>
				<td>${person.age}</td>
				<td>${person.zip}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>