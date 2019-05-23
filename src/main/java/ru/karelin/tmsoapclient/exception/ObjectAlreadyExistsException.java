package ru.karelin.tmsoapclient.exception;

public class ObjectAlreadyExistsException extends IllegalArgumentException {
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
}
