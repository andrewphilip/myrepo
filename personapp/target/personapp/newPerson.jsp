<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person - Creation</title>
</head>
<body>
	<form method="POST" action="add">
		<table>
			<tr>
				<td>
					Username:
				</td>
				<td>
					<input type="input" name="username" maxlength="40" />
				</td>
			</tr>
			<tr>
				<td>
					Firstname:
				</td>
				<td>
					<input type="input" name="firstname" maxlength="30" />
				</td>
			</tr>
			<tr>
				<td>
					Lastname:
				</td>
				<td>
					<input type="input" name="lastname" maxlength="30" />
				</td>
			</tr>
			<tr>
				<td>
					Age:
				</td>
				<td>
					<input type="input" name="age" maxlength="3" />
				</td>
			</tr>
			<tr>
				<td>
					Zipcode:
				</td>
				<td>
					<input type="input" name="zip" maxlength="5" />
				</td>
			</tr>
			<tr>
				<td>
					Gender:
				</td>
				<td>
					<select name="gender">
						<option  value="M" >Male</option>
						<option  value="F" >Female</option>
					</select>
				</td>
			</tr>
		</table>
		<input type="submit"  value="Save" />
	</form>
</body>
</html>