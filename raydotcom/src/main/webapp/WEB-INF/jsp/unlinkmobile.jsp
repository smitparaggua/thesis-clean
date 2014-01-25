<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form:form modelAttribute="user" method="POST">
    <table>
        <tr>
            <td><label for="usernameInput">Username:</label></td>
            <td><form:input type="text" id="usernameInput" path="username" required="true" autofocus="true" placeholder="Enter username"/></td>
        </tr>
        <tr>
            <td><label for="passwordInput">Password:</label></td>
            <td><form:input type="password" id="passwordInput" path="password" required="true" placeholder="Enter password"/></td>
        </tr>
        
        <%-- <tr>
            <td><label for="email">Email</label></td>
            <td><form:input type="text" id="email" path="email"/></td>
        </tr> --%>
        <tr>
            <td><input type="submit" value="Unlink"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
