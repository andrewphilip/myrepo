<html>
	<head>
		<title>Home page</title>
	</head>
	<body>
		<h2>Welcome Admin|User</h2>
		<br/>
		<p>It is now <c:out value="${now}"/></p>
		<br/>
		Please click the link to check if you have access to the resources.
		<br/>
		<a href="${pageContext.request.contextPath}/admin/index.jsp" >To the Admin Resources</a>
		<br/>
		<a href="${pageContext.request.contextPath}/pages/index.jsp" >To the User Resources</a>
	</body>
</html>