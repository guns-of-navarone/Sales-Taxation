/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.salestaxproblem;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This class provides a static rounding method
 * 
 * @author Craig
 */
public class Rounder {
    /**
     * This method takes an amount and rounds up to the nearest 0.05
     * 
     * @param amount
     * @return value(rounded)
     */
    public static BigDecimal roundUp(BigDecimal amount){
        BigDecimal twenty = new BigDecimal(20);
        MathContext mc = new MathContext(4);
        BigDecimal result =  new BigDecimal(amount.multiply(twenty)
                .add(new BigDecimal("0.5"), mc).toBigInteger(), mc).divide(twenty, mc);
        return result;
    }
}
