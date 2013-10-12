<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Signup success</title>
</head>
<body>
    You have successfully registered as <c:out value="${userRegistered.username}"/><br/>
    Mobile keyString:<c:out value="${mobileKey.keyString}"/><br/>

    <c:url var="loginUrl" value="/login"/>
    <a href="${loginUrl}">Login</a>
</body>
</html>