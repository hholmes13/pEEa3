/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hholmes
 */
public class MissingComponentException extends Exception {
    
    
    public MissingComponentException(){
        
    }
    
    public MissingComponentException(String message){
        super(message);
    }
    
}