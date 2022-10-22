package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.Group;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GroupImpl<T extends BaseStatement> extends BaseIngredient<T> implements Group<T> {

    private final ArrayList<String> qbGroupBy = new ArrayList<>();

    public GroupImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        if (!qbGroupBy.isEmpty()) {
            return Utils.implode(qbGroupBy, ", ");
        }
        return "";
    }

    @Override
    public void clear() {
        qbGroupBy.clear();
    }

    @Override
    public T groupBy(@NotNull String groupBy) {
        return groupBy(groupBy.split(","));
    }

    @Override
    public T groupBy(String @NotNull [] groupBy) {
        for (String val : groupBy) {
            if (val != null && !val.trim().isEmpty()) {
                qbGroupBy.add(val.trim());
            }
        }
        return mStatement;
    }
}
