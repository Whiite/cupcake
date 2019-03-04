/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ShoppingCart {

    /**
     * The shopping cart holds Line Items which has information of which cupcake
     * (bottom and topping) and the quantity of cupcakes. The Line Item also has
     * an invoice_id to prepare it for assignment 6. Create a ShoppingCart class
     * that has a list of LineItems (create this class too) The shopping cart
     * should be stored in the session (Why do you think?).
     */
    private List<LineItem> lineItems;
    private int invoiceid;

    public ShoppingCart() {
        this.lineItems = new ArrayList<>();
    }

    /**
     * Add line item
     *
     * @param lineItem
     */
    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    /**
     * 
     * @return 
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * Get invoice ID
     *
     * @return int
     */
    public int getInvoiceid() {
        return invoiceid;
    }

    /**
     * Set invoice ID
     *
     * @param invoiceid
     */
    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
    }

    /**
     * To String
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ShoppingCart{" + "lineItems=" + lineItems + ", invoiceid=" + invoiceid + '}';
    }

}
