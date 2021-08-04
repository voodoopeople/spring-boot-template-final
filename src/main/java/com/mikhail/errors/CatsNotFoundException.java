package com.mikhail.errors;

import java.net.URI;

public class CatsNotFoundException extends BadRequestAlertException{
    private static final long serialVersionUID = 1L;

    public CatsNotFoundException() {
        super(URI.create("/CatsNotFoundException"), "Cat not found!", "catsManagement", "catnotfound");
    }
}
