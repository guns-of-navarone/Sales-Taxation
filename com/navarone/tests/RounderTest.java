package com.navarone.tests;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.navarone.salestaxproblem.Rounder;

/**
 * This class tests the rounding capability. Mockito cannot test static methods.
 * 
 * @author Craig
 */
public class RounderTest {
    
    @Test
    public void testRounder(){
        HashMap<BigDecimal, BigDecimal> map = new HashMap<>();
        map.put(new BigDecimal(1), new BigDecimal(1));
        map.put(new BigDecimal(0.5), new BigDecimal(0.50, MathContext.DECIMAL32));
        map.put(new BigDecimal(0.63), new BigDecimal(0.65, MathContext.DECIMAL32));
        map.put(new BigDecimal(15.21), new BigDecimal(15.2, MathContext.DECIMAL32));
        map.put(new BigDecimal(100.99), new BigDecimal(101.00, MathContext.DECIMAL32));
        
        for(BigDecimal b:map.keySet()){
            assertEquals(Rounder.roundUp(b).compareTo(map.get(b)), 0);
        }
    }
}
