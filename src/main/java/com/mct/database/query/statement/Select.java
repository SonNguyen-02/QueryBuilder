package com.mct.database.query.statement;

import com.mct.database.query.ingredient.*;

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
