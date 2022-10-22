package com.mct.database.query.statement.impl;

import com.mct.database.query.enums.LikeType;
import com.mct.database.query.utils.exec.BuilderError;
import com.mct.database.query.ingredient.From;
import com.mct.database.query.ingredient.Limit;
import com.mct.database.query.ingredient.Where;
import com.mct.database.query.ingredient.impl.FromImpl;
import com.mct.database.query.ingredient.impl.LimitImpl;
import com.mct.database.query.ingredient.impl.WhereImpl;
import com.mct.database.query.statement.Delete;
import com.mct.database.query.utils.ClassDebug;
import org.jetbrains.annotations.NotNull;

public class DeleteImpl extends BaseStatementImpl implements Delete {

    private final From<Delete> mFrom;
    private final Where<Delete> mWhere;
    private final Limit<Delete> mLimit;

    public DeleteImpl() {
        checkCallerClass(ClassDebug.getCallerCallerClassName());
        mFrom = new FromImpl<>(this);
        mWhere = new WhereImpl<>(this);
        mLimit = new LimitImpl<>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete from(@NotNull String from) {
        return mFrom.from(from);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete groupStart() {
        return mWhere.groupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orGroupStart() {
        return mWhere.orGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete notGroupStart() {
        return mWhere.notGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orNotGroupStart() {
        return mWhere.orNotGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete groupEnd() {
        return mWhere.groupEnd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete where(String key, String value) {
        return mWhere.where(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete where(String where) {
        return mWhere.where(where);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orWhere(String key, String value) {
        return mWhere.orWhere(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orWhere(String where) {
        return mWhere.orWhere(where);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete whereIn(String key, String[] value) {
        return mWhere.whereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orWhereIn(String key, String[] value) {
        return mWhere.orWhereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete whereNotIn(String key, String[] value) {
        return mWhere.whereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orWhereNotIn(String key, String[] value) {
        return mWhere.orWhereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete like(String field, String match, LikeType type) {
        return mWhere.like(field, match, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orLike(String field, String match, LikeType likeType) {
        return mWhere.orLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete notLike(String field, String match, LikeType likeType) {
        return mWhere.notLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orNotLike(String field, String match, LikeType likeType) {
        return mWhere.orNotLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete limit(int limit) {
        return mLimit.limit(limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildStatement(boolean clear) {

        String from = compile(mFrom);
        String where = compile(mWhere);
        String limit = compile(mLimit);

        if (from.isEmpty() || where.isEmpty()) {
            throw new BuilderError("From & Where statement can't empty!");
        }

        if (clear) clearStatement();

        return ("DELETE FROM " + from + " WHERE " + where + limit).trim();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void clearStatement() {
        clear(mFrom, mWhere, mLimit);
    }
}
