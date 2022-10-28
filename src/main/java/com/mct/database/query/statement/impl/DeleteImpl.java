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
import org.jetbrains.annotations.NotNull;

public class DeleteImpl extends BaseStatementImpl implements Delete {

    private final From<Delete> mFrom;
    private final Where<Delete> mWhere;
    private final Limit<Delete> mLimit;

    @SuppressWarnings("unchecked")
    DeleteImpl() {
        mFrom = createIngredient(FromImpl.class);
        mWhere = createIngredient(WhereImpl.class);
        mLimit = createIngredient(LimitImpl.class);
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
    public Delete where(@NotNull String condition, Object... value) {
        return mWhere.where(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orWhere(@NotNull String condition, Object... value) {
        return mWhere.orWhere(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete whereIn(@NotNull String key, Object... value) {
        return mWhere.whereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orWhereIn(@NotNull String key, Object... value) {
        return mWhere.orWhereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete whereNotIn(@NotNull String key, Object... value) {
        return mWhere.whereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orWhereNotIn(@NotNull String key, Object... value) {
        return mWhere.orWhereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete like(@NotNull String field, String match) {
        return mWhere.like(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete like(@NotNull String field, String match, LikeType type) {
        return mWhere.like(field, match, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orLike(@NotNull String field, String match) {
        return mWhere.orLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.orLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete notLike(@NotNull String field, String match) {
        return mWhere.notLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete notLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.notLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orNotLike(@NotNull String field, String match) {
        return mWhere.orNotLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delete orNotLike(@NotNull String field, String match, LikeType likeType) {
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
