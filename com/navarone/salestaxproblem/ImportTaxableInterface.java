/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.salestaxproblem;

import java.math.BigDecimal;

import com.navarone.exceptions.UncalculatedException;

/**
 *
 * @author Craig
 */
public interface ImportTaxableInterface {
    /**
     * The import tax rate
     */
    final BigDecimal importrate      = new BigDecimal(0.05);
    /**
     * Calculates the import tax
     */
    public void calculateImportTax();
    
    /**
     * Returns the import tax if applicable
     * @return value (tax)
     * @throws UncalculatedException 
     */
    public BigDecimal getImportTax() throws UncalculatedException;
}
