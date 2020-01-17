package com.serheev.app.servlets;

import com.serheev.app.entities.UserEntity;
import com.serheev.app.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        UserEntity user = new UserEntity(name, password);

        /** Adding a user to the List */
//        Model model = Model.getInstance();
//        model.add(user);

        /** Adding a user to the Database */
        UserService userService = new UserService();
        userService.save(user);

        req.setAttribute("userName", name);
        doGet(req, resp);
    }
}
