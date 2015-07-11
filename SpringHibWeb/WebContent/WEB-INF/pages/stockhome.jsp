<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<body>
		<h4>Investors by Stock:</h4>
		<p>Stock Id:${stockid}</p>
		<b>Stock: ${stockObj.displayname}</b>		
		<div style="overflow: auto;height: 50%;">
			<table >
					<thead style="background-color: gray;border: 2px;color: yellow">
						<tr>
							<th>Investor Id</th>
							<th>First Name</th>
							<th>Last Name</th>
						</tr>
					</thead>
					<tbody>			
					<c:forEach items="${investors}" var="stockObj">
						<c:forEach items="${stockObj.investors}" var="invObj">
							<tr style="background-color: silver;border: 2px;">
								<td>${invObj.userid}</td>
								<td>${invObj.firstname}</td>
								<td>${invObj.lastname}</td>
							</tr>
						</c:forEach>
					</c:forEach>
					</tbody>
			</table>
		</div>
	</body>
</html>