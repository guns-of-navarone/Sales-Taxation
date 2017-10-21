/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.salestaxproblem;

import java.math.BigDecimal;

/**
 * Abstract class which implements the Item interface,
 * which has name and price
 * 
 * @author Craig
 */
public abstract class Good implements Item{
    private String name;
    private BigDecimal price;
    
    //Constructor
    public Good(String name, BigDecimal price){
        setName(name);
        setPrice(price);
    }
    /**
     * Returns the name
     * @return 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name
     * @param name 
     */
    @Override
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price
     * @return 
     */
    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Sets the price
     * @param price 
     */
    @Override
    public final void setPrice(BigDecimal price) {
        this.price = price;
    }
}
