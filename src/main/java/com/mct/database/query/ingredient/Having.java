package com.mct.database.query.ingredient;

import org.jetbrains.annotations.NotNull;

public interface Having<T> {
    /**
     * <strong>Having:</strong><br/>
     * Sets the HAVING value. Separates multiple calls with AND
     * -VD:
     * <br/>&nbsp;&nbsp;having("id = ?", 1);
     * <br/>&nbsp;&nbsp;having("age != ?", 15);
     * <br/>&nbsp;&nbsp;having("age is null");
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp;HAVING id = '1';
     * <br/>&nbsp;&nbsp;AND age != '15';
     * <br/>&nbsp;&nbsp;AND age is null;
     * <br/>
     * <b>-Note:</b><br/>
     * Conditional sentences must not contain 2 question marks next to each other.<br/>
     * <span style="color: #ff4c18">eg: .having("id = ? ?", 1) => An error throw</span><p/>
     *
     * @param condition The field to search
     * @param value     The values searched on
     * @return this
     * <br/>see {@link Condition#prepareCondition(String, Object...)}
     */
    T having(@NotNull String condition, Object... value);

    /**
     * <strong>Or Having:</strong><br/>
     * Sets the HAVING value. Separates multiple calls with OR
     * -VD:
     * <br/>&nbsp;&nbsp;orHaving("id = ?", 1);
     * <br/>&nbsp;&nbsp;orHaving("age != ?", 15);
     * <br/>&nbsp;&nbsp;orHaving("age is null");
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp;HAVING id = '1';
     * <br/>&nbsp;&nbsp;OR age != '15';
     * <br/>&nbsp;&nbsp;OR age is null;
     * <br/>
     * <b>-Note:</b><br/>
     * Conditional sentences must not contain 2 question marks next to each other.<br/>
     * <span style="color: #ff4c18">eg: .orHaving("id = ? ?", 1) => An error throw</span><p/>
     *
     * @param condition The field to search
     * @param value     The values searched on
     * @return this
     * <br/>see {@link Condition#prepareCondition(String, Object...)}
     */
    T orHaving(@NotNull String condition, Object... value);

}
