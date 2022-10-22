package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.Distinct;
import com.mct.database.query.statement.BaseStatement;

public class DistinctImpl<T extends BaseStatement> extends BaseIngredient<T> implements Distinct<T> {

    private boolean mDistinct;

    public DistinctImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        return mDistinct ? "DISTINCT" : "";
    }

    @Override
    public void clear() {
        mDistinct = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T distinct() {
        mDistinct = true;
        return mStatement;
    }
}
