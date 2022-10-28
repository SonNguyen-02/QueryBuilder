package com.mct.database.query;

import com.mct.database.query.statement.*;
import com.mct.database.query.statement.impl.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class StatementFactory {

    @NotNull
    public static Select createSelectStatement() {
        return createStatement(SelectImpl.class);
    }

    @NotNull
    public static Insert createInsertStatement() {
        return createStatement(InsertImpl.class);
    }

    @NotNull
    public static Update createUpdateStatement() {
        return createStatement(UpdateImpl.class);
    }

    @NotNull
    public static Delete createDeleteStatement() {
        return createStatement(DeleteImpl.class);
    }

    @SuppressWarnings("unchecked")
    static <Statement extends BaseStatement> Statement createStatement(@NotNull Class<Statement> clazz) {
        try {
            return (Statement) mMethod.invoke(null, clazz);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    static final Method mMethod;

    static {
        try {
            mMethod = _StatementFactory.class.getDeclaredMethod("newInstance", Class.class);
            mMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private StatementFactory() {
        throw new RuntimeException("Can't instance this class!");
    }

}
