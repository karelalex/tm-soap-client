package ru.karelin.tmsoapclient.exception;

public class CommandRegisteredException extends ObjectAlreadyExistsException{
    public CommandRegisteredException(String message) {
        super(message);
    }
}
