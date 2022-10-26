package com.mct.database.query.statement.impl;

import com.mct.database.query.ingredient.impl.BaseIngredient;
import com.mct.database.query.ingredient.impl.ValueImpl;
import com.mct.database.query.ingredient.impl._IngredientFactory;
import com.mct.database.query.statement.BaseStatement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

abstract class BaseStatementImpl implements BaseStatement {

    private final Method createIngredient;

    BaseStatementImpl() {
        try {
            createIngredient = _IngredientFactory.class.getDeclaredMethod("newInstance", Class.class, BaseStatement.class);
            createIngredient.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected <Ingredient extends BaseIngredient<? extends BaseStatement>>
    Ingredient createIngredient(@NotNull Class<Ingredient> clazz) {
        try {
            return (Ingredient) createIngredient.invoke(null, clazz, this);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

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

    @Override
    public String buildStatement() {
        return buildStatement(true);
    }
}
