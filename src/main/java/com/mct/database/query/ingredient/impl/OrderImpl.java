package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.Order;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderImpl<T extends BaseStatement> extends BaseIngredient<T> implements Order<T> {

    private final ArrayList<String> qbOrderBy = new ArrayList<>();

    public OrderImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        if (!qbOrderBy.isEmpty()) {
            return Utils.implode(qbOrderBy, ", ");
        }
        return "";
    }

    @Override
    public void clear() {
        qbOrderBy.clear();
    }

    /**
     * <strong>_orderBy:</strong><br/>
     *
     * @param orderBy   orderBy
     * @param direction ASC or DESC
     * @return this
     */
    private T _orderBy(String @NotNull [] orderBy, String direction) {
        for (String val : orderBy) {
            if (!val.trim().isEmpty()) {
                this.qbOrderBy.add(val.trim() + direction);
            }
        }
        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orderByAsc(@NotNull String orderBy) {
        return _orderBy(orderBy.split(","), " ASC");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orderByAsc(String[] orderBy) {
        return _orderBy(orderBy, " ASC");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orderByDesc(@NotNull String orderBy) {
        return _orderBy(orderBy.split(","), " DESC");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orderByDesc(String[] orderBy) {
        return _orderBy(orderBy, " DESC");
    }

}
