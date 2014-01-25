<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>Log In</head>
<body>
    <p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
    <p><c:out value="${error}"/></p>
    <p><c:url var="verifyLogin" value="/verify-login"/></p>
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
                <c:url value="/sign-up" var="signupUrl"/>
                <td><a href=${signupUrl}>Sign Up</a></td>
            </tr>
            <tr>
                <c:url var="unlinkMobileUrl" value="/unlink-mobile"/>
                <td><a href=${unlinkMobileUrl}>Unlink Mobile</td>
            </tr>
        </table>
    </form>
</body>
</html>