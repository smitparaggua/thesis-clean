<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>

    <body>
        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        <s:url var="verifyLogin" value="/verify-login"/>
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