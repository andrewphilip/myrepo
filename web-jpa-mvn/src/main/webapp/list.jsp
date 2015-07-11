<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Method Page</title>
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
<h1>Method List:</h1>

<table>
    <thead>
    <tr>
        <td>Method</td>
        <td>Description</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${methods}" var="method">
        <tr>
            <td><c:out value="${method.method}"/></td>
            <td><c:out value="${method.descr}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
