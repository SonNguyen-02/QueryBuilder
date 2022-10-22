package com.mct.database.query.ingredient;

import org.jetbrains.annotations.NotNull;

public interface Group<T> {
    /**
     * <strong>Group By:</strong><br>
     *
     * @param groupBy groupBy
     * @return this
     */
    T groupBy(@NotNull String groupBy);

    /**
     * <strong>Group By:</strong><br>
     *
     * @param groupBy groupBy
     * @return this
     */
    T groupBy(@NotNull String[] groupBy);

}
