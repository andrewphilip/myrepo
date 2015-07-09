<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head><title>Login Page</title>
<style type="text/css">
	#loginbox{
			width: 400px;
			background:silver;
			border: 1px solid #000;
			text-align: center;
	}
	
</style>
</head>
<body>
   <h4>Login</h4>
		<c:if test="${not empty error}">
			<div>Invalid username and password</div>
		</c:if>
   
   <div id="loginbox">
		   <form name='f' action="j_spring_security_check" method='POST'>
		      <table>
		         <tr>
		            <td>User:</td>
		            <td><input type='text' name='j_username' value=''></td>
		         </tr>
		         <tr>
		            <td>Password:</td>
		            <td><input type='password' name='j_password' /></td>
		         </tr>
		         <tr>
		            <td colspan="2"><input  name="submit" type="submit" value="Submit" /></td>
		         </tr>
		      </table>
					 <input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
		      
		  </form>
  </div>
</body>
</html>
