package com.mct.database.query.ingredient.impl;

import com.mct.database.query.statement.BaseStatement;

public abstract class BaseIngredient<T extends BaseStatement> {

    protected final T mStatement;

    public BaseIngredient(T statement) {
        this.mStatement = statement;
    }

    public abstract String compile();

    public abstract void clear();

}
