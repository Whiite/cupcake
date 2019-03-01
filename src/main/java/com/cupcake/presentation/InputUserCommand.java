/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import com.cupcake.data.UserDAO;
import com.cupcake.data.UserDataMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Malte
 */
public class InputUserCommand extends Command {



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDataMapper db = new UserDataMapper();
        UserDAO dao = new UserDAO(db);

        dao.makeUser(username, password, email);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("Welcome " + username);

        out.print("<a href='Login'>  Buy Cupcakes  </a>");

        out.close();

    }

}