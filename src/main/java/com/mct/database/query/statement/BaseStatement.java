package com.mct.database.query.statement;

public interface BaseStatement {

    /**
     * <strong>Build Statement:</strong><br>
     * Get compiled query string<br>
     * Build an statement query and returns the query string
     *
     * @param clear If enabled, the data will be cleared
     * @return query
     */
    String buildStatement(boolean clear);

    /**
     * <strong>Clear Statement:</strong><br>
     * If called, the data will be cleared
     */
    void clearStatement();
}
