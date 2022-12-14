package com.mct.database.query.statement.impl;

import com.mct.database.query.enums.LikeType;
import com.mct.database.query.ingredient.*;
import com.mct.database.query.ingredient.impl.*;
import com.mct.database.query.statement.Update;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class UpdateImpl extends BaseStatementImpl implements Update {

    private final From<Update> mFrom;
    private final Value<Update> mValue;
    private final Where<Update> mWhere;
    private final Order<Update> mOrder;
    private final Limit<Update> mLimit;

    @SuppressWarnings("unchecked")
    UpdateImpl() {
        mFrom = createIngredient(FromImpl.class);
        mValue = createIngredient(ValueImpl.class);
        mWhere = createIngredient(WhereImpl.class);
        mOrder = createIngredient(OrderImpl.class);
        mLimit = createIngredient(LimitImpl.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update from(@NotNull String from) {
        return mFrom.from(from);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update set(HashMap<String, Object> dataSet) {
        return mValue.set(dataSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update set(String key, Object value) {
        return mValue.set(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update groupStart() {
        return mWhere.groupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orGroupStart() {
        return mWhere.orGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update notGroupStart() {
        return mWhere.notGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orNotGroupStart() {
        return mWhere.orNotGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update groupEnd() {
        return mWhere.groupEnd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update where(@NotNull String condition, Object... value) {
        return mWhere.where(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orWhere(@NotNull String condition, Object... value) {
        return mWhere.orWhere(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update whereIn(@NotNull String key, Object... value) {
        return mWhere.whereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orWhereIn(@NotNull String key, Object... value) {
        return mWhere.orWhereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update whereNotIn(@NotNull String key, Object... value) {
        return mWhere.whereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orWhereNotIn(@NotNull String key, Object... value) {
        return mWhere.orWhereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update like(@NotNull String field, String match) {
        return mWhere.like(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update like(@NotNull String field, String match, LikeType type) {
        return mWhere.like(field, match, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orLike(@NotNull String field, String match) {
        return mWhere.orLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.orLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update notLike(@NotNull String field, String match) {
        return mWhere.notLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update notLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.notLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orNotLike(@NotNull String field, String match) {
        return mWhere.orNotLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orNotLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.orNotLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orderByAsc(String orderBy) {
        return mOrder.orderByAsc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orderByAsc(String[] orderBy) {
        return mOrder.orderByAsc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orderByDesc(String orderBy) {
        return mOrder.orderByDesc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orderByDesc(String[] orderBy) {
        return mOrder.orderByDesc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update limit(int limit) {
        return mLimit.limit(limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildStatement(boolean clear) {
        StringBuilder valueStr = new StringBuilder();
        castVal(mValue).getValues().forEach((k, v) -> valueStr.append(k).append(" = ").append(v).append(", "));
        valueStr.replace(valueStr.length() - 2, valueStr.length(), "");

        String from = compile(mFrom);
        String where = compile(mWhere);
        String order = compile(mOrder);
        String limit = compile(mLimit);

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(from).append(" SET ").append(valueStr);
        if (!where.isEmpty()) {
            sql.append(" WHERE ").append(where);
        }
        if (!order.isEmpty()) {
            sql.append(" ORDER BY ").append(order);
        }
        sql.append(limit);

        if (clear) clearStatement();

        return sql.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearStatement() {
        clear(mFrom, mValue, mWhere, mOrder, mLimit);
    }
}
