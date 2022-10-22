package com.mct.database.query.ingredient;

import java.util.HashMap;

public interface Value<T> {
    /**
     * <strong>Set:</strong><br>
     * Allows key/value pairs to be set for inserting or updating.
     *
     * @param dataSet key <=> column
     * @return this
     */
    T set(HashMap<String, String> dataSet);

    /**
     * <strong>Set:</strong><br>
     * Allows key/value pairs to be set for inserting or updating.
     *
     * @param key   key <=> column
     * @param value value
     * @return this
     */
    T set(String key, String value);

}
