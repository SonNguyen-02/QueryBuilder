package com.mct.database.query.statement;

import com.mct.database.query.ingredient.*;

public interface Update extends
        From<Update>,
        Value<Update>,
        Where<Update>,
        Order<Update>,
        Limit<Update>,
        BaseStatement {
}
