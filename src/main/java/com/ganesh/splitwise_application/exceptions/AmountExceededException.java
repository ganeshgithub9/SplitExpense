package com.ganesh.splitwise_application.exceptions;

public class AmountExceededException extends Exception{
    public AmountExceededException(String msg){
        super(msg);
    }
}
