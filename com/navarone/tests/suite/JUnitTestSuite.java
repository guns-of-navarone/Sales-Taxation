package com.navarone.tests.suite;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.navarone.tests.BasicGoodTest;
import com.navarone.tests.BasicGoodTestWithMockito;
import com.navarone.tests.InvalidFileTest;
import com.navarone.tests.RounderTest;
import com.navarone.tests.ValidFileTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({ 
   BasicGoodTest.class, 
   BasicGoodTestWithMockito.class, 
   RounderTest.class,
   ValidFileTest.class,
   InvalidFileTest.class
})
public class JUnitTestSuite {
}
