package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.Limit;
import com.mct.database.query.statement.BaseStatement;

public class LimitImpl<T extends BaseStatement> extends BaseIngredient<T> implements Limit<T> {

    private int qbLimit;
    private int qbOffset;

    LimitImpl() {
    }

    @Override
    public String compile() {
        String compile = "";
        if (qbLimit > 0) {
            compile = "LIMIT " + qbLimit;
            if (qbOffset > 0) compile += " OFFSET " + qbOffset;
        }
        return compile;
    }

    @Override
    public void clear() {
        qbLimit = 0;
        qbOffset = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T limit(int limit, int offset) {
        if (limit < 0 || offset < 0) {
            throw new IllegalAccessError("Limit and offset >= 0");
        }
        this.qbLimit = limit;
        this.qbOffset = offset;
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T limit(int limit) {
        return limit(limit, 0);
    }
}
