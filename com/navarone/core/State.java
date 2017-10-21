/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.core;

/**
 * Models the states of a finite state machine.
 * @author Craig
 */
public enum State {
    INIT(0),
    INPUT(1),
    ITEM(2),
    EOF(3),
    ERROR(4);
    
    private final int code;

    
    private State(int code){
        this.code = code;
    }
    
    
    
    public final int getCode(){
        return this.code;
    }

}
