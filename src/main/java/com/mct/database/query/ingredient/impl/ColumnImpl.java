package com.mct.database.query.ingredient.impl;

import com.mct.database.query.ingredient.Column;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ColumnImpl<T extends BaseStatement> extends BaseIngredient<T> implements Column<T> {

    private final ArrayList<String> qbColumns = new ArrayList<>();

    public ColumnImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        if (!qbColumns.isEmpty()) {
            return Utils.implode(qbColumns, ", ");
        }
        return "";
    }

    @Override
    public void clear() {
        qbColumns.clear();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public T column() {
        return column("*");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T column(@NotNull String columns) {
        for (String val : columns.split(",")) {
            if (!(val = val.trim()).isEmpty()) {
                qbColumns.add(val);
            }
        }
        return mStatement;
    }
}
