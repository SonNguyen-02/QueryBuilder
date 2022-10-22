package com.mct.database.query.utils.exec;

public class BuilderError extends RuntimeException {

    private final String msg;

    public BuilderError(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}
