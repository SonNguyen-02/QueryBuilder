package com.mct.database.query.ingredient;

import org.jetbrains.annotations.NotNull;

public interface Having<T> {
    /**
     * <strong>Having:</strong><br/>
     * Sets the HAVING value. Separates multiple calls with AND
     *
     * @param key   The field to search
     * @param value The values searched on
     * @return this
     */
    T having(@NotNull String key, String value);

    /**
     * <strong>Having:</strong><br/>
     * SQL query joined with AND if appropriate<br/>
     * -VD: <br/>
     * &nbsp;&nbsp;having("name='Joe' AND status='boss'");<br/>
     * -Produces: <br/>
     * &nbsp;&nbsp;HAVING name='Joe' AND status='boss';
     *
     * @param having having string
     * @return this
     */
    T having(@NotNull String having);

    /**
     * <strong>Or Having:</strong><br/>
     * Sets the HAVING value. Separates multiple calls with OR
     *
     * @param key   The field to search
     * @param value The values searched on
     * @return this
     */
    T orHaving(@NotNull String key, String value);

    /**
     * <strong>Having:</strong><br/>
     * SQL query joined with OR if appropriate<br/>
     * -VD:
     * <br/>&nbsp;&nbsp;having("name='Joe' AND status='boss'");
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp;OR name='Joe' AND status='boss';
     *
     * @param having having string
     * @return this
     */
    T orHaving(@NotNull String having);
}
