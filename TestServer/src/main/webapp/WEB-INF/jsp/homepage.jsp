<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
    </head>

    <body>
        <h4>Login Success!</h4>
        <c:url var="logoutLink" value="/logout"/>
        <p><a href="${logoutLink}">Logout</a></p>
    </body>
</html>