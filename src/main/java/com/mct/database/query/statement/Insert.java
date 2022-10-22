package com.mct.database.query.statement;

import com.mct.database.query.StatementFactory;
import com.mct.database.query.ingredient.From;
import com.mct.database.query.ingredient.Value;

/**
 * <strong>Insert: </strong>
 * An interface is responsible for creating the <b>insert statement</b>
 * <p/>
 * -VD:
 * <pre style="color: #509a32">
 * {@link StatementFactory#createInsertStatement()}
 *     .from("account")
 *     .set("name", "nam")
 *     .set("age", "12")
 *     .set("gender", "male")
 *     .set("job", "teacher")
 *     .buildStatement(true);
 * </pre>
 * -Produces:
 * <pre style="color: #2b8cff">
 * INSERT INTO account (name, gender, job, age) VALUE ('nam', 'male', 'teacher', '12')
 * </pre>
 * To get an instance:<br/>
 * See also: {@link StatementFactory#createInsertStatement()}
 */
public interface Insert extends
        From<Insert>,
        Value<Insert>,
        BaseStatement {
}
