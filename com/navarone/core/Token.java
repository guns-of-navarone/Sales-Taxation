/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.core;

/**
 *
 * @author Craig
 */
public enum Token {
    INPUT(0),
    ITEM(1),
    ERROR(2);
    
    private final int code;

    
    private Token(int code){
        this.code = code;
    }
    
    
    
    public final int getCode(){
        return this.code;
    }
}
