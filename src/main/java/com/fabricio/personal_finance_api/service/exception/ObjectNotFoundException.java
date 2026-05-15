package com.fabricio.personal_finance_api.service.exception;

import java.io.Serial;

public class ObjectNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8945436357277514172L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
