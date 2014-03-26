package sample;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.core.WarriorService;
import com.csg.warrior.core.exception.WarriorRequestException;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if(Validate.checkUser(email, pass))
        {
            if(isWarriorLocked(email)) {
                out.println("User is locked by warrior system");
            } else {
                RequestDispatcher rs = request.getRequestDispatcher("Welcome");
                rs.forward(request, response);
            }
        }
        else
        {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        }

    }

    private boolean isWarriorLocked(String email) {
        try {
            WarriorService warriorService = new WarriorService("http://localhost:8080/testserver", "servlet");
            WarriorKeyStatus status = warriorService.getWarriorKeyStatus(email, "servlet");
            return status.isRegisteredInWarrior() && status.isWarriorKeyValid();
        } catch (WarriorRequestException e) {
            return true; // does not inform user if unexpected error occurs
        }
    }
}
