package com.mct.database.query.statement.impl;

import com.mct.database.query.statement.BaseStatement;
import org.jetbrains.annotations.NotNull;

public class _StatementFactory {

    private _StatementFactory() {
        throw new RuntimeException("Can't instance this class!");
    }

    @NotNull
    @SuppressWarnings("unused")
    static <Statement extends BaseStatement> Statement
    newInstance(@NotNull Class<Statement> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
