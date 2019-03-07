<%@page import="com.cupcake.data.UserDataMapper"%>
<%@page import="com.cupcake.data.User"%>
<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="com.cupcake.data.LineItem"%>
<%@page import="com.cupcake.data.CupcakeDataMapper"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.Top"%>
<%@page import="com.cupcake.data.Bottom"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

    <h1> SHOP </h1>

<%  /* Pulling the User out from the SQL */
    User user = (User) session.getAttribute("user");

    /* Instance of relevant DataMapper */
    CupcakeDataMapper db = new CupcakeDataMapper();
    UserDataMapper userdb = new UserDataMapper();

    /* Shows which user is logged in */
    out.println("<h2> " + user.getUsername() + " is logged in.</h2>");

    /* Shows the users balance */
    out.println("<p style=\"font-size:18px\"> "
            + "Users Balance: " + user.getBalance() + "</p>");

    /* Add button and field to add balance to User */
%>

<p> Add Balance: </p> 
<form action="Controller?command=Product" method="post">
    <input type="hidden" name="origin" value="add balance">
    Amount:        <input type="text" name="amount"/>
    <input type="submit" value="add"/>
</form>

<%  /* Add balance END */
// Dropdown menu:
    /* Pulling the tops and bottoms of the cupcakes out of SQL */
    List<Top> tops = db.getTops();
    List<Bottom> bots = db.getBottoms();

    /* Form for dropdowns BEGIN */
%>
<form action="Controller?command=Product" method="post">
    <input type="hidden" name="origin" value="addProduct">
    <table class="table table-striped">
        <thead><tr><th>Bottom</th><th>Topping</th><th>Quantity</th><th>Select</th><th></th></tr></thead>
        <tbody>
            <tr>
                <td><select name="bottom" id="bottomSelect">
                        <%    for (Bottom bot : bots) {
                                out.print("<option value=\"" + bot.getName()
                                        + "\">" + bot.getName() + "</option>\n");
                            }

                            out.print("<select>\n");
                            out.print("<td><select name=\"top\" id=\"topSelect\">\n");

                            for (Top top : tops) {
                                out.print("<option value=\"" + top.getName()
                                        + "\">" + top.getName() + "</option>\n");
                            }
                        %>
                    </select>
                <td><input type="text" name="qty" placeholder="quantity" id="qtyInput"></td>
                <td><input type="submit" name="submit" value="Add to cart"></td><td><span id="errorContainer"></span></td>
            </tr>
        </tbody>
    </table>
</form>
<%
    /* Form for dropdows END */
    // ShoppingCart:
    /* Form for ShoppingCart START */
    ShoppingCart cart = user.getCart();

    out.println("<h2> Shopping Cart: </h2>");

    // prints total price of the cart
    if (cart != null) {
        out.print("<h3> Total Price of Cart: " + user.getTotalPrice() + "$</h3>");
    }

    if (cart == null) {

    } else {
        /* Prints every LineItem in the cart in a list. */
        List<LineItem> items = cart.getLineItems();
        for (LineItem item : items) {
            String tname = item.getCupcake().getTop().getName();
            String bname = item.getCupcake().getBottom().getName();
            out.println("<p style=\"font-size:18px\"> "
                    + "Cupcake: " + item.toString() + "</p>"
                    /* Button to remove the ListItem */
                    + "<form action=\"Controller?command=Product\" method=\"post\">\n"
                    + "    <input type=\"hidden\" name=\"origin\" value=\"removeitem\">\n"
                    + "    <input type=\"hidden\" name=\"cake\" value=\"" + bname + tname + "\">\n"
                    + "    <input type=\"submit\" value=\"Remove\"/>\n"
                    + "</form>"
            );
        }
    }
    /* Form for ShoppingCart END */

 /* Cart Checkout Start */
    if ((cart != null && !cart.isEmpty())) {
%>
<p> Checkout Entire Cart and make it an invoice: </p> 
<form action="Controller?command=Product" method="post">
    <input type="hidden" name="origin" value="checkout">
    <input type="submit" value="Checkout"/>
</form>

<%
} else {
%>

<p>
    Either cart is empty or you do not have enough money for items in Cart.
    Add more money, or remove items in Cart to be able to checkout.
</p>

<%
    }
    /* Cart Checkout End */
%>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>
