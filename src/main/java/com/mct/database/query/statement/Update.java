package com.mct.database.query.statement;

import com.mct.database.query.StatementFactory;
import com.mct.database.query.ingredient.*;

/**
 * <strong>Update: </strong>
 * An interface is responsible for creating the <b>update statement</b>
 * <p/>
 * -VD:
 * <pre style="color: #509a32">
 * {@link StatementFactory#createUpdateStatement()}
 *     .from("account")
 *     .set("name", "nam")
 *     .set("age", "12")
 *     .set("gender", "male")
 *     .set("job", "teacher")
 *     .where("a.id >", "5")
 *     .where("a.name", "HUY")
 *     .whereNotIn("a.age", new String[]{"22", "44", "34"})
 *     .buildStatement(true);
 * </pre>
 * -Produces:
 * <pre style="color: #2b8cff">
 * UPDATE account
 * SET name = 'nam', gender = 'male', job = 'teacher', age = '12'
 * WHERE a.id > '5' AND a.name = 'HUY' AND a.age NOT IN ('22', '44', '34')
 * </pre>
 * To get an instance:<br/>
 * See also: {@link StatementFactory#createUpdateStatement()}
 */
public interface Update extends
        From<Update>,
        Value<Update>,
        Where<Update>,
        Order<Update>,
        Limit<Update>,
        BaseStatement {
}
