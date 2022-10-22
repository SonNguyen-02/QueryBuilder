package com.mct.database.query.ingredient;

import com.mct.database.query.enums.JoinType;

public interface Join<T> {

    /**
     * <strong>Join:</strong><br>
     * <p>
     * Generates the JOIN portion of the query.<br>
     * -VD: <br>&nbsp;&nbsp; db.join("comments", "comments.id =
     * student.id").<br>
     * -Produces: <br>&nbsp;&nbsp; JOIN comments ON comments.id =
     * student.id.
     *
     * @param table Table name
     * @param cond  Condition
     * @return this
     */
    T join(String table, String cond);

    /**
     * <strong>Join:</strong><br>
     * Generates the JOIN portion of the query.<br>
     * -VD: <br>&nbsp;&nbsp; join("comments", "comments.id = student.id",
     * JoinType.LEFT).<br>
     * -Produces: <br>&nbsp;&nbsp; LEFT JOIN comments ON comments.id =
     * student.id.
     *
     * @param table Table name
     * @param cond  Condition
     * @param type  type
     * @return this
     */
    T join(String table, String cond, JoinType type);

}
