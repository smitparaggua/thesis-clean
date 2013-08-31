<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>

    <body>
        <c:url var="verifyLogin" value="/verifyLogin"/>
        <form action="${verifyLogin}" method="POST">
            <table>
                <tr>
                    <td><label for="usernameInput">Username:</label></td>
                    <td><input type="text" id="usernameInput" required autofocus placeholder="Enter username"/></td>
                </tr>
                <tr>
                    <td><label for="paswordInput">Password:</label></td>
                    <td><input type="password" id="paswordInput" required autofocus placeholder="Enter password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>