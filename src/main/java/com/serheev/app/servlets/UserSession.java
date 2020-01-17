package com.serheev.app.servlets;

import com.serheev.app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class UserSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(10);
        String sessionId = session.getId();
        String name = (String) session.getAttribute("userName");
        Integer visit = (Integer) session.getAttribute("userVisit");
        Date creationTime = new Date(session.getCreationTime());
        Date lastVisitTime = new Date(session.getLastAccessedTime());

        Model model = Model.getInstance();
        List<String> names = model.list();
        String randomName;

        if(names.size() > 0){
            Random rand = new Random();
            randomName = names.get(rand.nextInt(names.size()));
        } else {
            randomName = "No Name";
        }

        if (name == null) {
            name = "Anonym";
            visit = 1;
        } else {
            if (visit < 2) {
                name = randomName;
            }
            visit++;
        }

        session.setAttribute("userName", name);
        session.setAttribute("userVisit", visit);

        if (visit == 10) {
            session.invalidate();
        }

        req.setAttribute("userName", name);
        req.setAttribute("visit", visit);
        req.setAttribute("creationTime", creationTime);
        req.setAttribute("lastVisitTime", lastVisitTime);
        req.setAttribute("sessionId", sessionId);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user.jsp");
        requestDispatcher.forward(req, resp);
    }
}
