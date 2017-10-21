/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.exceptions;

/**
 * If the item has not been registered in the Basic Tax Register
 * then this exception will be thrown.
 * 
 * @author Craig
 */
public class ItemNotRegisteredException extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotRegisteredException(){
        
    }
    
    public ItemNotRegisteredException(String message){
        super(message);
    }
}
