package com.mct.database.query.ingredient.impl;

import com.mct.database.query.statement.BaseStatement;

public abstract class BaseIngredient<T extends BaseStatement> {

    public abstract String compile();

    public abstract void clear();

    private T mStatement;

    T getStatement() {
        return mStatement;
    }

    void setStatement(T mStatement) {
        this.mStatement = mStatement;
    }

}
