package com.navarone.tests;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.navarone.exceptions.UncalculatedException;
import com.navarone.salestaxproblem.BasicGood;

import java.math.BigDecimal;
import org.junit.Before;

import org.mockito.Mockito;
import org.junit.Test;
/**
 * This test tests BasicGood with Mockito
 * @author Craig
 */
public class BasicGoodTestWithMockito {

    public BasicGoodTestWithMockito() {
    }
    
    @Before
    public void init(){
    }
    
    @Test
    public void basicTest(){
        BasicGood b = Mockito.mock(BasicGood.class);
        b.setName("name");
        b.setPrice(BigDecimal.ZERO);
        Mockito.when(b.getName()).thenReturn("name");
        Mockito.when(b.getPrice()).thenReturn(BigDecimal.ZERO);        
    }
    
    @Test
    public void basicTaxTest() throws UncalculatedException{
        BasicGood b = Mockito.mock(BasicGood.class);
        b.setName("name");
        b.setPrice(new BigDecimal(5));
        b.setBasicTaxable(true);
        b.setImportedTaxable(false);
        b.getTotalTax();
        Mockito.when(b.getBasicTax()).thenReturn(new BigDecimal(0.5));
    }
    
    @Test
    public void importedTaxTest() throws UncalculatedException{
        BasicGood b = Mockito.mock(BasicGood.class);
        b.setName("name");
        b.setPrice(new BigDecimal(5));
        b.setBasicTaxable(false);
        b.setImportedTaxable(true);
        b.getTotalTax();
        Mockito.when(b.getImportTax()).thenReturn(new BigDecimal(0.25));
    }
    
    @Test
    public void bothTaxesTest() throws UncalculatedException{
        BasicGood b = Mockito.mock(BasicGood.class);
        b.setName("name");
        b.setPrice(new BigDecimal(5));
        b.setBasicTaxable(true);
        b.setImportedTaxable(true);
        b.getTotalTax();
        Mockito.when(b.getImportTax()).thenReturn(new BigDecimal(0.25));
        Mockito.when(b.getBasicTax()).thenReturn(new BigDecimal(0.5));
        Mockito.when(b.getTotalTax()).thenReturn(new BigDecimal(0.75));
    }
}
