package com.mct.database.query.ingredient;

import org.jetbrains.annotations.NotNull;

public interface From<T> {
    /**
     * <strong>From:</strong><br/>
     * The table you want query.<br/>
     * -VD 1:
     * <br/>&nbsp;&nbsp; db.select().from("student").from("mark")
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp; SELECT * FROM student, mark
     * <p/>
     * -VD 2:
     * <br/>&nbsp;&nbsp; db.delete().from("student")
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp; DELETE FROM student...
     *
     * @param from table
     * @return this
     */
    T from(@NotNull String from);
}
