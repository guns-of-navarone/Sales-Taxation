/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.salestaxproblem;

import java.math.BigDecimal;
import java.math.MathContext;

import com.navarone.exceptions.UncalculatedException;

/**
 * An Good which is billable through its interfaces.
 * It may attract import tax, basic tax, none or both
 * 
 * @author Craig
 */
public class BasicGood extends Good implements BasicTaxableInterface, ImportTaxableInterface{    
        
    private final BigDecimal ZERO           = new BigDecimal(0);
    
    private BigDecimal basicTax             = null;
    private BigDecimal importTax            = null;
    private BigDecimal totalTax             = null;
    private String errorMessage             = null;
    private boolean basicTaxable            = false;
    private boolean importedTaxable         = false;
    
    //Constructor
    public BasicGood(String name, BigDecimal price, boolean basicTaxable, boolean importedTaxable){
        super(name, price);
        this.errorMessage       = "Tax is not yet calculated.";
        this.basicTaxable       = basicTaxable;
        this.importedTaxable    = importedTaxable;
    }

    public boolean isBasicTaxable() {
        return basicTaxable;
    }

    public boolean isImportedTaxable() {
        return importedTaxable;
    }
    
    public void setBasicTaxable(boolean taxable) {
        this.basicTaxable = taxable;
    }
    
    public void setImportedTaxable(boolean taxable) {
        this.importedTaxable = taxable;
    }
    /**
     * Calculate basic tax
     */
    @Override
    public void calculateBasicTax(){
        if(basicTaxable){
            MathContext context = new MathContext(5);
            this.basicTax       = getPrice().multiply(basicrate, context);
        }else{
            this.basicTax       = ZERO;
        }
    }
    /**
     * Calculate import tax
     */
    @Override
    public void calculateImportTax(){
        if(importedTaxable){
            MathContext context = new MathContext(5);
            this.importTax      = getPrice().multiply(importrate, context);
        }else{
            this.importTax      = ZERO;
        }
    }
    
    /**
     * Gets the import tax of the item
     * @return
     * @throws UncalculatedException
     */
    @Override
    public BigDecimal getImportTax() throws UncalculatedException{
        if(this.importTax == null)
            throw new UncalculatedException(errorMessage);
        else
            return this.importTax;
    }
    /**
     * Gets the basic tax of the item
     * @return
     * @throws UncalculatedException 
     */
    @Override
    public BigDecimal getBasicTax() throws UncalculatedException{
        if(this.basicTax == null)
            throw new UncalculatedException(errorMessage);
        else
            return this.basicTax;
    }
    /**
     * Returns the total tax applicable
     * @return
     * @throws UncalculatedException 
     */
    public BigDecimal getTotalTax() throws UncalculatedException{
        if(this.totalTax != null)
            return this.totalTax;
        this.calculateBasicTax();
        this.calculateImportTax();
        this.totalTax = getImportTax().add(getBasicTax());
        return this.totalTax;
    }
}
