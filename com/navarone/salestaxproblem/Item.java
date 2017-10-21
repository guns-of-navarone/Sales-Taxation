/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.salestaxproblem;

import java.math.BigDecimal;

/**
 * Interface which specifies a few methods for a billable item
 * @author Craig
 */
public interface Item {
    /**
     * Returns the name
     * @return 
     */
    public String getName();
    /**
     * Sets the name
     * @param name 
     */
    public void setName(String name);
    /**
     * Returns the price
     * @return 
     */
    public BigDecimal getPrice();
    /**
     * Sets the price
     * @param price 
     */
    public void setPrice(BigDecimal price);
}
