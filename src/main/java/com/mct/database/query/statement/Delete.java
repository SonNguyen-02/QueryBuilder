package com.mct.database.query.statement;

import com.mct.database.query.ingredient.From;
import com.mct.database.query.ingredient.Limit;
import com.mct.database.query.ingredient.Where;

public interface Delete extends
        From<Delete>,
        Where<Delete>,
        Limit<Delete>,
        BaseStatement {
}
