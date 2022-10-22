package com.mct.database.query.ingredient;

public interface Distinct<T> {
    /**
     * <strong>Distinct</strong><br>
     * Distinct make sentence <strong>SELECT</strong> has DISTINCT.<br>
     * -VD: <br>&nbsp;&nbsp; db.select().distinct().from("student")<br>
     * -Produces: <br>&nbsp;&nbsp; SELECT DISTINCT * FROM student
     *
     * @return this
     */
    T distinct();
}
