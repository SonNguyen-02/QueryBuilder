package com.mct.database.query.statement;

import com.mct.database.query.StatementFactory;
import com.mct.database.query.ingredient.From;
import com.mct.database.query.ingredient.Limit;
import com.mct.database.query.ingredient.Where;

/**
 * <strong>Delete: </strong>
 * An interface is responsible for creating the <b>delete statement</b>
 * <p/>
 * -VD:
 * <pre style="color: #509a32">
 * {@link StatementFactory#createDeleteStatement()}
 *     .from("account")
 *     .where("id >", "5")
 *     .where("name", "HUY")
 *     .whereNotIn("age", new String[]{"5", "8", "15"})
 *     .buildStatement(true);
 * </pre>
 * -Produces:
 * <pre style="color: #2b8cff">
 * DELETE FROM account WHERE id > '5' AND name = 'HUY' AND a.age NOT IN ('5', '8', '15')
 * </pre>
 * To get an instance:<br/>
 * See also: {@link StatementFactory#createDeleteStatement()}
 */
public interface Delete extends
        From<Delete>,
        Where<Delete>,
        Limit<Delete>,
        BaseStatement {
}
