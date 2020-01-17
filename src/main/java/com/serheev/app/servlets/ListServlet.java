package com.serheev.app.servlets;

import com.serheev.app.entities.UserEntity;
import com.serheev.app.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /** Reading users from List */
//        Model model = Model.getInstance();
//        List<String> names = model.list();

        /** Reading users from Database */
        UserService userService = new UserService();
        List<UserEntity> users = userService.getAll();
        List<String> names = users.stream().map(UserEntity::getName).collect(Collectors.toList());

        req.setAttribute("userNames", names);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
