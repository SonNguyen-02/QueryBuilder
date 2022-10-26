package com.mct.database.query.ingredient.impl;

import com.mct.database.query.enums.JoinType;
import com.mct.database.query.ingredient.Join;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;

import java.util.ArrayList;

public class JoinImpl<T extends BaseStatement> extends BaseIngredient<T> implements Join<T> {

    private final ArrayList<String> qbJoin = new ArrayList<>();

    JoinImpl() {
    }

    @Override
    public String compile() {
        return Utils.implode(qbJoin, "\n");
    }

    @Override
    public void clear() {
        qbJoin.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T join(String table, String cond) {
        return join(table, cond, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T join(String table, String cond, JoinType type) {
        String typeStr = type == null ? "" : type + " ";
        String join = typeStr + "JOIN " + table + " ON " + cond;
        this.qbJoin.add(join);

        return getStatement();
    }
}
