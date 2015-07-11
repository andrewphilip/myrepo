<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<body>
		<h4>This is a Spring Digest Authentication - Home page.</h4>
		<div>
			<a href="/digest/admin/index.jsp">Admin page</a>
			<a href="<c:url value="/j_spring_security_logout" />" >Spring Security Logout</a>		
		</div>	
	</body>
</html>


