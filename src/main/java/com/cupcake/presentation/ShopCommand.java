/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import com.cupcake.data.Bottom;
import com.cupcake.data.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cupcake.data.CupcakeDataMapper;
import com.cupcake.data.Top;
import java.util.List;

/**
 * TO DO
 *
 * @author
 */
public class ShopCommand extends Command {

    /* 
    
    User comes here from LoginCommand.
    
    Shows who is logged in. Shows their balance.
    
    Also displays a dropdown of Toppings and a dropdown of Bottoms.
    Also has a field to input Quantity of Cupcakes you want to add to your cart.
    
    Uses ProductCommand.
    
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        CupcakeDataMapper db = new CupcakeDataMapper();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");

            out.println("<head>");
            out.println("<title>Servlet ShopServlet</title>");
            out.println("</head>");

            out.println("<body>");

            /* Shows who is logged in */
            out.println("<h1> " + user.getName() + " is logged in.</h1>");
            /* Shows their balance */
            out.println("<p style=\"font-size:18px\"> "
                    + "Users Balance: " + user.getBalance() + "</p>");
            
            List<Top> tops = db.getTops();
            List<Bottom> bots = db.getBottoms();
            
            /* Form for dropdowns */
            out.println(
                    "<form id=\"addProduct\" action=\"Control\" method=\"POST\">\n"
                    + "                        <input type=\"hidden\" name=\"origin\" value=\"addProduct\">\n"
                    + "                        <table class=\"table table-striped\">\n"
                    + "                            <thead><tr><th>Bottom</th><th>Topping</th><th>Quantity</th><th>Select</th><th></th></tr></thead>\n"
                    + "                            <tbody>\n"
                    + "                                <tr>\n"
                    + "<td><select name=\"bottom\" id=\"bottomSelect\">\n");
            for (Bottom bot : bots) {
                out.print("<option value=\"" + bot.getName() + "\">" + bot.getName() + "</option>\n");
            }
            out.print("<select>\n");
            out.print("<td><select name=\"top\" id=\"topSelect\">\n");
            for (Top top : tops) {
                out.print("<option value=\"" + top.getName() + "\">" + top.getName() + "</option>\n");
            }
            out.print("</select>\n"
                    + "<td><input type=\"text\" name=\"qty\" placeholder=\"quantity\" id=\"qtyInput\"></td>\n"
                    + "<td><input type=\"submit\" name=\"submit\" value=\"Add to cart\"></td><td><span id=\"errorContainer\"></span></td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</form>"
            );

            out.println("</body>");

            out.println("</html>");
        }
    }

}
