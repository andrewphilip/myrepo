<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Area Detail page</title>
</head>
<body>
<h1>Area Description:</h1>

<table>
    <tbody>
        <tr>
        	<td style="background-color: yellow;">Areaseq:</td>
            <td><c:out value="${viewarea.areaseq}"/></td>
        </tr>    
        <tr>
        	<td style="background-color: yellow;">Areacode:</td>
            <td><c:out value="${viewarea.areacode}"/></td>
        </tr>    
        <tr>
        	<td style="background-color: yellow;">Method:</td>
            <td><c:out value="${viewarea.method}"/></td>
        </tr>    
        <tr>
        	<td style="background-color: yellow;">Statenum:</td>
            <td><c:out value="${viewarea.statenum}"/></td>
        </tr>    
        <tr>
        	<td style="background-color: yellow;">Areatype:</td>
            <td><c:out value="${viewarea.areatype}"/></td>
        </tr>    
        <tr>
        	<td style="background-color: yellow;">Title:</td>
            <td><c:out value="${viewarea.title}"/></td>
        </tr>    
    </tbody>
</table>
</body>
</html>
