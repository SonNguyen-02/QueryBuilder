package com.mct.database.query.ingredient.impl;

import com.mct.database.query.utils.exec.BuilderError;
import com.mct.database.query.ingredient.Value;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ValueImpl<T extends BaseStatement> extends BaseIngredient<T> implements Value<T> {

    private final HashMap<String, Object> qbSet = new HashMap<>();

    public ValueImpl(T statement) {
        super(statement);
    }

    public HashMap<String, Object> getValues() {
        return new HashMap<>(qbSet);
    }

    @Override
    public String compile() {
        return "";
    }

    @Override
    public void clear() {
        qbSet.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T set(@NotNull HashMap<String, Object> dataSet) {
        dataSet.forEach((key, value) -> {
            if (key == null || key.isEmpty()) {
                throw new BuilderError("Key is empty");
            }
            if (value != null) {
                dataSet.replace(key, Utils.escape(value.toString()));
            }
        });
        qbSet.putAll(dataSet);
        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T set(@NotNull String key, Object value) {
        if (key.isEmpty()) {
            throw new BuilderError("Key is empty");
        }
        HashMap<String, Object> dataSet = new HashMap<>();
        dataSet.put(key, value);
        return set(dataSet);
    }

}
