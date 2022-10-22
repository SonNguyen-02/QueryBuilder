package com.mct.database.query.ingredient;

import org.jetbrains.annotations.NotNull;

public interface Column<T> {

    /**
     * <strong>Column</strong><br>
     * Select list column.<br>
     * -VD: <br>&nbsp;&nbsp; db.column().from("student")<br>
     * -Produces: <br>&nbsp;&nbsp; SELECT * FROM student
     *
     * @return this
     */
    T column();

    /**
     * <strong>Column</strong><br>
     * Select list column.<br>
     * -VD: <br>&nbsp;&nbsp; db.select("id, name").from("student")<br>
     * -Produces: <br>&nbsp;&nbsp; SELECT id, name FROM student
     *
     * @param selects column to select
     * @return this
     */
    T column(@NotNull String selects);
}
