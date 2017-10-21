package com.navarone.tests;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.navarone.core.Register;

/**
 * This class tests a valid file named input-valid.txt in resources folder.
 * The expected output is stored in output-valid.txt in same folder.
 * 
 * @author Craig
 */
public class ValidFileTest {
    
    @Test
    public void test(){        
        String path = new File("").getAbsolutePath();
        String inpath = path + File.separator + 
                "resources" + File.separator + "input-valid.txt";
        System.out.println(inpath);
        Register register = new Register(inpath);
        register.run();

        String outpath = path + File.separator + 
                "resources" + File.separator + "output-valid.txt";
        BufferedReader br;
        FileReader fr;
        String line, out = "";
        try{
            fr              = new FileReader(outpath);
            br              = new BufferedReader(fr);
            
            while((line = br.readLine()) != null){
                out = out+ line + "\n";
            }            
        } catch (FileNotFoundException ex) {
            fail(ex.getLocalizedMessage());
        } catch (IOException io) {
            fail(io.getLocalizedMessage());
        } 
              
        assertEquals(Register.getBuffer(), out);
    }
}
