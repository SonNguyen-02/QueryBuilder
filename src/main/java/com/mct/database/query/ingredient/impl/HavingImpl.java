package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.Condition;
import com.mct.database.query.ingredient.Having;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HavingImpl<T extends BaseStatement> extends BaseIngredient<T> implements Condition, Having<T> {

    private final ArrayList<String> qbHaving = new ArrayList<>();

    public HavingImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        if (!qbHaving.isEmpty()) {
            return Utils.implode(qbHaving, "\n");
        }
        return "";
    }

    @Override
    public void clear() {
        qbHaving.clear();
    }

    private T _hv(String key, Object value, String type) {
        qbHaving.add((qbHaving.isEmpty() ? "" : type) + _whv(key, value));
        return mStatement;
    }

    private T _hv(String having, String type) {
        qbHaving.add((qbHaving.isEmpty() ? "" : type) + _whv(having));
        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T having(@NotNull String key, Object value) {
        return _hv(key, value, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T having(@NotNull String having) {
        return _hv(having, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orHaving(@NotNull String key, Object value) {
        return _hv(key, value, "OR ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orHaving(@NotNull String having) {
        return _hv(having, "OR ");
    }

}
