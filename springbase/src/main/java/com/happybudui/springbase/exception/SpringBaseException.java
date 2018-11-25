package com.happybudui.springbase.exception;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

public class SpringBaseException extends RuntimeException{

    private int errorcode;

    private String message;

    public SpringBaseException(){
        super();
    }

    public SpringBaseException(int errorcode){
        super("error occurred!");
        this.errorcode=errorcode;
        this.message="error!";
    }

    public SpringBaseException(String message){
        super(message);
        this.errorcode=200;
        this.message=message;
    }

    public SpringBaseException(int errorcode, String message ){
        super(message);
        this.errorcode=errorcode;
        this.message=message;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
