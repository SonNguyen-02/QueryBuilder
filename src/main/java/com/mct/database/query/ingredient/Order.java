package com.mct.database.query.ingredient;

public interface Order<T> {

    /**
     * <strong>Order By ASC:</strong><br>
     * Sets the ORDER BY value
     *
     * @param orderBy orderBy
     * @return this
     */
    T orderByAsc(String orderBy);

    /**
     * <strong>Order By ASC:</strong><br>
     * Sets the ORDER BY value
     *
     * @param orderBy orderBy
     * @return this
     */
    T orderByAsc(String[] orderBy);

    /**
     * <strong>Order By DESC:</strong><br>
     * Sets the ORDER BY value
     *
     * @param orderBy orderBy
     * @return this
     */
    T orderByDesc(String orderBy);

    /**
     * <strong>Order By DESC:</strong><br>
     * Sets the ORDER BY value
     *
     * @param orderBy orderBy
     * @return this
     */
    T orderByDesc(String[] orderBy);

}
