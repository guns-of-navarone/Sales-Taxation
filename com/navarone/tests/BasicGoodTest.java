package com.navarone.tests;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.navarone.exceptions.UncalculatedException;
import com.navarone.salestaxproblem.BasicGood;
import com.navarone.salestaxproblem.BasicTaxableInterface;
import com.navarone.salestaxproblem.ImportTaxableInterface;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Rounding to the nearest 0.01
 * @author Craig
 */
public class BasicGoodTest {
    
    private static final List<BasicGood> list = new ArrayList<>();
    private static final List<String> name = new ArrayList<>();
    static{
        name.add("imported perfume");
        name.add("imported chocolate");
        name.add("perfume");
        name.add("chocolate");
        list.add(new BasicGood(name.get(0), new BigDecimal(20.0)
                , true, true));
        list.add(new BasicGood(name.get(1), new BigDecimal(20.0)
                , true, false));
        list.add(new BasicGood(name.get(2), new BigDecimal(20.0)
                , false, true));
        list.add(new BasicGood(name.get(3), new BigDecimal(20.0)
                , false, false));
    }
    /**
     * Standard test not using Mockito. Each good is checked
     */
    @Test
    public void createGoodTest(){
        int count = 0;
        for(BasicGood good: list){            

            try {
                good.getTotalTax();
                assertEquals(good.getName(), name.get(count));
                if(good.isBasicTaxable()){
                    BigDecimal a = good.getPrice().add(good.getBasicTax())
                            .round(new MathContext(2));
                    BigDecimal b = good.getPrice()
                            .multiply(BasicTaxableInterface.basicrate);
                            
                    b = b.add(good.getPrice()).round(new MathContext(2));

                    assertEquals(a.compareTo(b), 0);
                }
                if(good.isImportedTaxable()){
                    BigDecimal a = good.getPrice().add(good.getImportTax())
                            .round(new MathContext(2));
                    BigDecimal b = good.getPrice()
                            .multiply(ImportTaxableInterface.importrate);
                            
                    b = b.add(good.getPrice()).round(new MathContext(2));

                    assertEquals(a.compareTo(b), 0);
                }
                count++;
            } catch (UncalculatedException ex) {
                fail(ex.getLocalizedMessage());
            }
        }   
    }
}
