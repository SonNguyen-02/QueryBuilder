package com.mct.database.query.ingredient;

public interface Limit<T> {
    /**
     * <strong>Limit:</strong><br/>
     * Sets the LIMIT and offset values
     * the select statement need offset value. Default only need limit value
     *
     * @param limit  limit
     * @param offset offset
     * @return this
     */
    default T limit(int limit, int offset) {
        return limit(limit);
    }

    /**
     * <strong>Limit:</strong><br/>
     * Sets the LIMIT value and offset = 0
     *
     * @param limit limit
     * @return this
     */
    T limit(int limit);
}
