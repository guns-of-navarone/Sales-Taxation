/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.exceptions;

/**
 * If tax is not computed but queried, then this exception will be thrown.
 * 
 * @author Craig
 */
public class UncalculatedException extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UncalculatedException(){}
    
    public UncalculatedException(String message){
        super(message);
    }
}
