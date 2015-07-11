<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<body>
		<h4>Users:</h4>
		<table style="width: 90%;">
				<tr>
					<td>
						<table style="width: 80%;">
									<thead style="background-color: gray;border: 2px;color: yellow">
										<th width="25%">Name</th>
										<th width="25%">UserId</th>
										<th width="15%">Statecode</th>
										<th width="35%">Email</th>
									</thead>
						</table>
					</td>	
				</tr>
				<tr>
					<td>	<div style="width:100%;overflow-y:scroll;position:relative;height:250px;">
							<table style="width:81%;">
									<tbody>			
									<c:forEach items="${users}" var="user">
										<tr style="background-color: silver;border: 2px;">
											<td width="25%">${user.name}</td>
											<td width="25%">${user.userid}</td>
											<td width="15%">${user.statecode}</td>
											<td width="35%">${user.email}</td>
										</tr>
									</c:forEach>
									</tbody>
							</table>
							</div>
					</td>
				</tr>
		</table>
	</body>
</html>