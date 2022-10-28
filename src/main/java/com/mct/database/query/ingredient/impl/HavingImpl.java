package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.Condition;
import com.mct.database.query.ingredient.Having;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HavingImpl<T extends BaseStatement> extends BaseIngredient<T> implements Condition, Having<T> {

    private final ArrayList<String> qbHaving = new ArrayList<>();

    HavingImpl() {
    }

    @Override
    public String compile() {
        if (!qbHaving.isEmpty()) {
            return Utils.implode(qbHaving, " ");
        }
        return "";
    }

    @Override
    public void clear() {
        qbHaving.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T having(@NotNull String condition, Object... value) {
        qbHaving.add((qbHaving.isEmpty() ? "" : "AND ") + prepareCondition(condition, value));
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orHaving(@NotNull String condition, Object... value) {
        qbHaving.add((qbHaving.isEmpty() ? "" : "OR ") + prepareCondition(condition, value));
        return getStatement();
    }
}
