package sample;

import com.csg.warrior.core.WarriorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sample.WarriorConfig.HOST_NAME;
import static sample.WarriorConfig.WARRIOR_URL;

public class WarriorKeyRequest extends HttpServlet {
    private WarriorService warriorService = new WarriorService("http://localhost:8080/testserver", "servlet");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO GET PARAMETERS
        //warriorService.forwardKeyRequestToWARServer(WARRIOR_URL, HOST_NAME);
    }
}
