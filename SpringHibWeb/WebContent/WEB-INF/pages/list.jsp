<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<body>
		<h4>List of Stocks:</h4>
		<div style="overflow: auto;height: 50%;">
			<table >
					<thead style="background-color: gray;border: 2px;color: yellow">
						<tr>
							<th>Stockid</th>
							<th>Market</th>
							<th>Ticker</th>
							<th>Display Name</th>
						</tr>
					</thead>
					<tbody>			
					<c:forEach items="${stocks}" var="st">
						<tr style="background-color: silver;border: 2px;">
							<td>${st.stockid}</td>
							<td>${st.market}</td>
							<td>${st.ticker}</td>
							<td>${st.displayname}</td>
						</tr>
					</c:forEach>
					</tbody>
			</table>
		</div>
	</body>
</html>