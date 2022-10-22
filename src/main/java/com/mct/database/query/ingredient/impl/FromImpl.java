package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.From;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FromImpl<T extends BaseStatement> extends BaseIngredient<T> implements From<T> {

    private final ArrayList<String> qbFrom = new ArrayList<>();

    public FromImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        if (!qbFrom.isEmpty()) {
            return Utils.implode(qbFrom, ", ");
        }
        return "";
    }

    @Override
    public void clear() {
        qbFrom.clear();
    }

    @Override
    public T from(@NotNull String from) {
        for (String val : from.split(",")) {
            this.qbFrom.add(val.trim());
        }
        return mStatement;
    }
}
