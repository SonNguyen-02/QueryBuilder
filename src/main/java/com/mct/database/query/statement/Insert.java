package com.mct.database.query.statement;

import com.mct.database.query.ingredient.From;
import com.mct.database.query.ingredient.Value;

public interface Insert extends
        From<Insert>,
        Value<Insert>,
        BaseStatement {
}
