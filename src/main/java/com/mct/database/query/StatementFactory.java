package com.mct.database.query;

import com.mct.database.query.statement.*;
import com.mct.database.query.statement.impl.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class StatementFactory {

    @NotNull
    public static Select createSelectStatement() {
        return FactoryHelper.LAZY_HOLDER.INSTANCE.createStatement(SelectImpl.class);
    }

    @NotNull
    public static Insert createInsertStatement() {
        return FactoryHelper.LAZY_HOLDER.INSTANCE.createStatement(InsertImpl.class);
    }

    @NotNull
    public static Update createUpdateStatement() {
        return FactoryHelper.LAZY_HOLDER.INSTANCE.createStatement(UpdateImpl.class);
    }

    @NotNull
    public static Delete createDeleteStatement() {
        return FactoryHelper.LAZY_HOLDER.INSTANCE.createStatement(DeleteImpl.class);
    }

    private StatementFactory() {
        throw new RuntimeException("Can't instance this class!");
    }

    private static class FactoryHelper {

        private static class LAZY_HOLDER {
            private static final FactoryHelper INSTANCE = new FactoryHelper();
        }

        private final Method createStatement;

        private FactoryHelper() {
            try {
                createStatement = _StatementFactory.class.getDeclaredMethod("newInstance", Class.class);
                createStatement.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        @SuppressWarnings("unchecked")
        private <Statement extends BaseStatement> Statement
        createStatement(@NotNull Class<Statement> clazz) {
            try {
                return (Statement) createStatement.invoke(null, clazz);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

    }
}
