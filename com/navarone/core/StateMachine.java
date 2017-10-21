/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.core;

import com.navarone.exceptions.MalformedInputFileException;

/**
 * Models a state machine
 * 
 * @author Craig
 */
public class StateMachine {
    
    private static final State[][] transition = {  
                                    { State.INPUT, State.ERROR, State.ERROR },
                                    { State.ERROR, State.ITEM, State.ERROR },
                                    { State.INPUT, State.ITEM, State.ERROR },
                                    { State.ERROR, State.ERROR, State.ERROR },
                                    { State.ERROR, State.ERROR, State.ERROR }  
                                };
    
    public static State transit(State state, Token token) 
            throws MalformedInputFileException{
        if(transition[state.getCode()][token.getCode()] == State.ERROR){
            throw new MalformedInputFileException
            ("The input file is not as per the specified format.");
        }
        return transition[state.getCode()][token.getCode()];
    }
    
}
