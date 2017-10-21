package com.navarone.tests;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.navarone.core.Register;
import com.navarone.core.State;

/**
 * This file tests inputs which are not as per the standard format.
 * The input files are in the /resources/ folder
 * @author Craig
 */
public class InvalidFileTest {
    
    /**
     * This tests an invalid item. The item is in the file:
     * 1 imported bottle of perfume at at 27.99
     */
    @Test
    public void testInValidItem(){        
        String path = new File("").getAbsolutePath();
        String inpath = path + File.separator + 
                "resources" + File.separator + "input-invalid1.txt";
        Register register = new Register(inpath);
        register.run();         
              
        assertEquals(Register.getState(), State.ERROR);
    }
    
    /**
     * This tests an invalid file
     * The file contains an extra line not matching any pattern
     */
    @Test
    public void testInvalidExtraLine(){        
        String path = new File("").getAbsolutePath();
        String inpath = path + File.separator + 
                "resources" + File.separator + "input-invalid2.txt";
        Register register = new Register(inpath);
        register.run();         
              
        assertEquals(Register.getState(), State.ERROR);
    }
}
