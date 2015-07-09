<html>
	<head>
		<title>Login Page</title>
	</head>
	<body>
		<p>Login Form</p>
		 <form method='POST' action='j_security_check'>
		 	<table>
		 		<tr>
		 			<td> Username: <input type='text' name='j_username' /> </td>
			 	</tr> 
			 	<tr>
			 	 	<td>Password: <input type='password' name='j_password' /></td>
			 	</tr> 	 
		 	 </table> 
		 	 <br/>
		 	 <input type='submit' value='Login' />
		 	 <input type='reset' value='Reset' />
		 </form>
	</body>
</html>
