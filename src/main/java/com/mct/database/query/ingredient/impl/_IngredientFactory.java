package com.mct.database.query.ingredient.impl;

import com.mct.database.query.statement.BaseStatement;
import org.jetbrains.annotations.NotNull;

public class _IngredientFactory {

    private _IngredientFactory() {
        throw new RuntimeException("Can't instance this class!");
    }

    @NotNull
    @SuppressWarnings("unused")
    static <Statement extends BaseStatement, Ingredient extends BaseIngredient<Statement>>
    Ingredient newInstance(@NotNull Class<Ingredient> clazz, @NotNull Statement statement) {
        try {
            Ingredient ingredient = clazz.getDeclaredConstructor().newInstance();
            BaseIngredient.class
                    .getDeclaredMethod("setStatement", BaseStatement.class)
                    .invoke(ingredient, statement);
            return ingredient;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
