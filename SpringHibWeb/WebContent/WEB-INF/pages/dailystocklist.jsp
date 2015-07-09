<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<body>
		<h4>Daily Stock Records:</h4>
		<div style="overflow: auto;height: 50%;">
			<table style="width: 95%">
					<thead style="background-color: gray;border: 2px;color: yellow;">
						<tr>
							<th style="width: 5%">Ticker</th>
							<th style="width: 15%">Display Name</th>
							<th style="width: 10%">Date</th>
							<th style="width: 10%">Price</th>
							<th style="width: 10%">Open</th>
							<th style="width: 10%">Close</th>
							<th style="width: 15%">Volume</th>
							<th style="width: 10%">High</th>
							<th style="width: 10%">Low</th>
						</tr>
					</thead>
					<tbody>			
					<c:forEach items="${dailystockrecords}" var="ds">
						<tr style="background-color: silver;border: 2px;">
							<td style="width: 5%">${ds.stock.ticker}</td>
							<td style="width: 15%">${ds.stock.displayname}</td>
							<td style="width: 10%">${ds.stockdate}</td>
							<td style="width: 10%">${ds.price}</td>
							<td style="width: 10%">${ds.stockopen}</td>
							<td style="width: 10%">${ds.stockclose}</td>
							<td style="width: 15%">${ds.volume}</td>
							<td style="width: 10%">${ds.stockhigh}</td>
							<td style="width: 10%">${ds.stocklow}</td>
						</tr>
					</c:forEach>
					</tbody>
			</table>
		</div>
	</body>
</html>