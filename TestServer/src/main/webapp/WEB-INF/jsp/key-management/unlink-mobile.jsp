<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Unlink Mobile</title>
</head>

<body>
<c:out value="${errorMessage}"/>
<s:url var="unlinkMobileUrl" value="/verify-login"/>
<form action="${verifyLogin}" method="POST">
    <table>
        <tr>
            <td><label for="usernameInput">Username:</label></td>
            <td><input type="text" id="usernameInput" name="username" required autofocus placeholder="Enter username"/></td>
        </tr>
        <tr>
            <td><label for="passwordInput">Password:</label></td>
            <td><input type="password" id="passwordInput" name="password" required placeholder="Enter password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"/></td>
        </tr>
        <tr>
            <s:url value="/signup" var="signupUrl"/>
            <td><a href=${signupUrl}>Signup</a></td>
        </tr>
        <tr>
            <s:url var="unlinkMobileUrl" value="/unlink-mobile"/>
            <td><a href=${unlinkMobileUrl}>Unlink Mobile</a> (for mobile key users)</td>
        </tr>
    </table>
</form>
</body>
</html>
