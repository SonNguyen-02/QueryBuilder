package com.mct.database.query;

import com.mct.database.query.statement.Delete;
import com.mct.database.query.statement.Insert;
import com.mct.database.query.statement.Select;
import com.mct.database.query.statement.Update;
import com.mct.database.query.statement.impl.DeleteImpl;
import com.mct.database.query.statement.impl.InsertImpl;
import com.mct.database.query.statement.impl.SelectImpl;
import com.mct.database.query.statement.impl.UpdateImpl;
import org.jetbrains.annotations.NotNull;

public class StatementFactory {

    @NotNull
    public static Select createSelectStatement() {
        return new SelectImpl();
    }

    @NotNull
    public static Insert createInsertStatement() {
        return new InsertImpl();
    }

    @NotNull
    public static Update createUpdateStatement() {
        return new UpdateImpl();
    }

    @NotNull
    public static Delete createDeleteStatement() {
        return new DeleteImpl();
    }

}
