package com.gevernova.addressbook.exceptionhandler;

public class EntryNotFoundException extends RuntimeException{
    public EntryNotFoundException(String message){
        super(message);
    }
}
