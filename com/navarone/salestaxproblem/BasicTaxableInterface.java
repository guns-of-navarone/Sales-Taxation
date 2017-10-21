/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.salestaxproblem;

import java.math.BigDecimal;

import com.navarone.exceptions.UncalculatedException;

/**
 * This interface specifies contract for those goods which attract basic tax.
 * 
 * @author Craig
 */
public interface BasicTaxableInterface {
    
    /**
     * The basic tax rate
     */
    final BigDecimal basicrate      = new BigDecimal(0.1);
    
    /**
     * Calculates the basic tax
     */
    public void calculateBasicTax();
    
    /**
     * Returns the basic tax calculated
     * @return value(tax)
     * @throws UncalculatedException 
     */
    public BigDecimal getBasicTax() throws UncalculatedException;
}
