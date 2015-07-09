<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify - Person</title>
</head>
<body>
	<form:form method="POST" action="update" modelAttribute="customer">
		<table>
			<tr>
				<td>
					Username:
				</td>
				<td>
					<form:input path="username" maxlength="40"/>
				</td>
			</tr>
			<tr>
				<td>
					Firstname:
				</td>
				<td>
					<form:input path="firstname" maxlength="30"/>
				</td>
			</tr>
			<tr>
				<td>
					Lastname:
				</td>
				<td>
					<form:input path="lastname" maxlength="30"/>
				</td>
			</tr>
			<tr>
				<td>
					Age:
				</td>
				<td>
					<form:input path="age" maxlength="3"/>
				</td>
			</tr>
			<tr>
				<td>
					Zipcode:
				</td>
				<td>
					<form:input path="zip" maxlength="5"/>
				</td>
			</tr>
			<tr>
				<td>
					Gender:
				</td>
				<td>
					<form:select path="gender">
						<form:option value="M">Male</form:option>
						<form:option value="F">Female</form:option>
					</form:select>
				</td>
			</tr>
		</table>
		<input type="submit"  value="update" />
	</form:form>

</body>
</html>