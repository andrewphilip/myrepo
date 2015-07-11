<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="appContext" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <title>Area Page</title>
    <script type="text/javascript" src="<c:out value='${appContext}'/>/javascript/jquery-simple-pagination-plugin.js"></script>
</head>
<body>
<div class="messages">
    <c:if test="${feedbackMessage != null}">
        <div class="messageblock"><c:out value="${feedbackMessage}"/></div>
    </c:if>
    <c:if test="${errorMessage != null}">
        <div class="errorblock"><c:out value="${errorMessage}"/></div>
    </c:if>
</div>
<h1>Area List:</h1>

		<center>
		<table width="850px;" >
		    <thead>
		    <tr style="background-color: yellow;" align="center">
		        <th width="10%">Areaseq</th>
		        <th width="25%">Areacode</th>
		        <th width="7%">Method</th>
		        <th width="10%">Statenum</th>
		        <th width="8%">Areatype</th>
		        <th width="40%">Title</th>
		    </tr>
		    </thead>
		 </table>

	<div style="height:400px;width:900px;overflow:auto;">
	
	 	<table width="850px;">   
	    <tbody style="overflow: auto;"  height="90%;">
	    <c:forEach items="${areas}" var="area">
	        <tr>
	            <td width="10%"><c:out value="${area.areaseq}"/></td>
	            <td width="25%"><c:out value="${area.areacode}"/></td>
	            <td width="7%"><c:out value="${area.method}"/></td>
	            <td width="10%"><c:out value="${area.statenum}"/></td>
	            <td width="8%"><c:out value="${area.areatype}"/></td>
	            <td width="40%"><c:out value="${area.title}"/></td>
	        </tr>
	    </c:forEach>
	    </tbody>
		</table>
	</div>
	</center>
</body>
</html>
