package com.mct.database.query.statement.impl;

import com.mct.database.query.ingredient.From;
import com.mct.database.query.ingredient.Value;
import com.mct.database.query.ingredient.impl.FromImpl;
import com.mct.database.query.ingredient.impl.ValueImpl;
import com.mct.database.query.statement.Insert;
import com.mct.database.query.utils.ClassDebug;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class InsertImpl extends BaseStatementImpl implements Insert {

    private final From<Insert> mFrom;
    private final Value<Insert> mValue;

    public InsertImpl() {
        checkCallerClass(ClassDebug.getCallerCallerClassName());
        mFrom = new FromImpl<>(this);
        mValue = new ValueImpl<>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Insert from(@NotNull String from) {
        return mFrom.from(from);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Insert set(HashMap<String, String> dataSet) {
        return mValue.set(dataSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Insert set(String key, String value) {
        return mValue.set(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildStatement(boolean clear) {
        String from = compile(mFrom);
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        castVal(mValue).getValues().forEach((k, v) -> {
            key.append(k).append(", ");
            value.append(v).append(", ");
        });
        key.replace(key.length() - 2, key.length(), "");
        value.replace(value.length() - 2, value.length(), "");

        if (clear) clearStatement();

        return "INSERT INTO " + from + " (" + key + ") VALUE (" + value + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearStatement() {
        clear(mFrom, mValue);
    }
}
