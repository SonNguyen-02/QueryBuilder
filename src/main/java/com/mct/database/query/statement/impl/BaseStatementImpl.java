package com.mct.database.query.statement.impl;

import com.mct.database.query.StatementFactory;
import com.mct.database.query.ingredient.impl.BaseIngredient;
import com.mct.database.query.ingredient.impl.ValueImpl;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.exec.IllegalCallerException;
import org.jetbrains.annotations.NotNull;

abstract class BaseStatementImpl implements BaseStatement {

    protected String compile(Object o) {
        return cast(o).compile();
    }

    protected void clear(Object @NotNull ... objects) {
        for (Object o : objects) {
            cast(o).clear();
        }
    }

    protected ValueImpl<? extends BaseStatement> castVal(Object o) {
        return (ValueImpl<? extends BaseStatement>) o;
    }

    protected BaseIngredient<? extends BaseStatement> cast(Object o) {
        return (BaseIngredient<? extends BaseStatement>) o;
    }

    protected void checkCallerClass(String callerClass) {
        if (!StatementFactory.class.getName().equals(callerClass)) {
            throw new IllegalCallerException("You don't have permission access to class '" + getClass().getSimpleName() + "'.");
        }
    }

    @Override
    public String buildStatement() {
        return buildStatement(true);
    }
}
