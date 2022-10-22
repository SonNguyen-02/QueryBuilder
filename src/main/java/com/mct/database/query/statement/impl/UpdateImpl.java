package com.mct.database.query.statement.impl;

import com.mct.database.query.enums.LikeType;
import com.mct.database.query.ingredient.*;
import com.mct.database.query.ingredient.impl.*;
import com.mct.database.query.statement.Update;
import com.mct.database.query.utils.ClassDebug;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class UpdateImpl extends BaseStatementImpl implements Update {

    private final From<Update> mFrom;
    private final Value<Update> mValue;
    private final Where<Update> mWhere;
    private final Order<Update> mOrder;
    private final Limit<Update> mLimit;

    public UpdateImpl() {
        checkCallerClass(ClassDebug.getCallerCallerClassName());
        mFrom = new FromImpl<>(this);
        mValue = new ValueImpl<>(this);
        mWhere = new WhereImpl<>(this);
        mOrder = new OrderImpl<>(this);
        mLimit = new LimitImpl<>(this);
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
    public Update where(String key, Object value) {
        return mWhere.where(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update where(String where) {
        return mWhere.where(where);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orWhere(String key, Object value) {
        return mWhere.orWhere(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orWhere(String where) {
        return mWhere.orWhere(where);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update whereIn(String key, Object[] value) {
        return mWhere.whereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orWhereIn(String key, Object[] value) {
        return mWhere.orWhereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update whereNotIn(String key, Object[] value) {
        return mWhere.whereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orWhereNotIn(String key, Object[] value) {
        return mWhere.orWhereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update like(String field, String match, LikeType type) {
        return mWhere.like(field, match, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orLike(String field, String match, LikeType likeType) {
        return mWhere.orLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update notLike(String field, String match, LikeType likeType) {
        return mWhere.notLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Update orNotLike(String field, String match, LikeType likeType) {
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
