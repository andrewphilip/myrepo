<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<style type="text/css">
			td#colheader {
				background-color: gray;
				color: yellow;
			}
			td#coldata {
				background-color: silver;
			}
		</style>
	</head>
	<body>
		<h4>User Details:</h4>
		<br/>
		<table>
					<tbody>
						<tr>
							<td id="colheader">Name:</td>
							<td>${user.name}</td>
						</tr>
						<tr>
							<td id="colheader">UserId:</td>
							<td>${user.userid}</td>
						</tr>
						<tr>
							<td id="colheader">Statecode:</td>
							<td>${user.statecode}</td>
						</tr>
						<tr>
							<td id="colheader">Email:</td>
							<td>${user.email}</td>
						</tr>
					</tbody>			
		</table>
	</body>
</html>