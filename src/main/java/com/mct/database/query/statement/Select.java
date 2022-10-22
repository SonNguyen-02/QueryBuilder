package com.mct.database.query.statement;

import com.mct.database.query.StatementFactory;
import com.mct.database.query.ingredient.*;

/**
 * <strong>Select: </strong>
 * An interface is responsible for creating the <b>select statement</b>
 * <p/>
 * -VD:
 * <pre style="color: #509a32">
 * {@link StatementFactory#createSelectStatement()}
 *     .column("name, code, age")
 *     .from("user")
 *     .join("product", "user.id = product.id", JoinType.RIGHT)
 *     .where("id >", "5")
 *     .groupStart()
 *     .orWhere("name", "Huy")
 *     .orWhereNotIn("age", new String[]{"22", "44", "34"})
 *     .groupEnd()
 *     .like("code", "111", LikeType.BEFORE)
 *     .orderByAsc("name")
 *     .limit(10, 23)
 *     .buildStatement(true);
 * </pre>
 * -Produces:
 * <pre style="color: #2b8cff">
 * SELECT name, code, age
 * FROM user
 * LEFT OUTER JOIN product ON user.id = product.id
 * WHERE id > '5' AND ( name = 'Huy' OR age NOT IN ('22', '44', '34') ) AND code LIKE '%111'
 * ORDER BY name ASC
 * LIMIT 10 OFFSET 23
 * </pre>
 * To get an instance:<br/>
 * See also: {@link StatementFactory#createSelectStatement()}
 */
public interface Select extends
        Distinct<Select>,
        Column<Select>,
        From<Select>,
        Join<Select>,
        Where<Select>,
        Group<Select>,
        Having<Select>,
        Order<Select>,
        Limit<Select>,
        BaseStatement {
}
