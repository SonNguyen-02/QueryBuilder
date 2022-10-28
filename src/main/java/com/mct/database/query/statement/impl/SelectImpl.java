package com.mct.database.query.statement.impl;

import com.mct.database.query.enums.JoinType;
import com.mct.database.query.enums.LikeType;
import com.mct.database.query.ingredient.*;
import com.mct.database.query.ingredient.impl.*;
import com.mct.database.query.statement.Select;
import org.jetbrains.annotations.NotNull;

public class SelectImpl extends BaseStatementImpl implements Select {

    private final Column<Select> mColumn;
    private final Distinct<Select> mDistinct;
    private final From<Select> mFrom;
    private final Join<Select> mJoin;
    private final Where<Select> mWhere;
    private final Group<Select> mGroup;
    private final Having<Select> mHaving;
    private final Order<Select> mOrder;
    private final Limit<Select> mLimit;

    @SuppressWarnings("unchecked")
    SelectImpl() {
        mColumn = createIngredient(ColumnImpl.class);
        mDistinct = createIngredient(DistinctImpl.class);
        mFrom = createIngredient(FromImpl.class);
        mJoin = createIngredient(JoinImpl.class);
        mWhere = createIngredient(WhereImpl.class);
        mGroup = createIngredient(GroupImpl.class);
        mHaving = createIngredient(HavingImpl.class);
        mOrder = createIngredient(OrderImpl.class);
        mLimit = createIngredient(LimitImpl.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select distinct() {
        return mDistinct.distinct();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select column() {
        return mColumn.column();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select column(@NotNull String selects) {
        return mColumn.column(selects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select from(@NotNull String from) {
        return mFrom.from(from);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select join(String table, String cond) {
        return mJoin.join(table, cond);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select join(String table, String cond, JoinType type) {
        return mJoin.join(table, cond, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select groupStart() {
        return mWhere.groupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orGroupStart() {
        return mWhere.orGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select notGroupStart() {
        return mWhere.notGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orNotGroupStart() {
        return mWhere.orNotGroupStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select groupEnd() {
        return mWhere.groupEnd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select where(@NotNull String condition, Object... value) {
        return mWhere.where(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orWhere(@NotNull String condition, Object... value) {
        return mWhere.orWhere(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select whereIn(@NotNull String key, Object... value) {
        return mWhere.whereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orWhereIn(@NotNull String key, Object... value) {
        return mWhere.orWhereIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select whereNotIn(@NotNull String key, Object... value) {
        return mWhere.whereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orWhereNotIn(@NotNull String key, Object... value) {
        return mWhere.orWhereNotIn(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select like(@NotNull String field, String match) {
        return mWhere.like(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select like(@NotNull String field, String match, LikeType type) {
        return mWhere.like(field, match, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orLike(@NotNull String field, String match) {
        return mWhere.orLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.orLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select notLike(@NotNull String field, String match) {
        return mWhere.notLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select notLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.notLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orNotLike(@NotNull String field, String match) {
        return mWhere.orNotLike(field, match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orNotLike(@NotNull String field, String match, LikeType likeType) {
        return mWhere.orNotLike(field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select groupBy(@NotNull String groupBy) {
        return mGroup.groupBy(groupBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select groupBy(@NotNull String[] groupBy) {
        return mGroup.groupBy(groupBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select having(@NotNull String condition, Object... value) {
        return mHaving.having(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orHaving(@NotNull String condition, Object... value) {
        return mHaving.orHaving(condition, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select limit(int limit, int offset) {
        return mLimit.limit(limit, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select limit(int limit) {
        return mLimit.limit(limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orderByAsc(String orderBy) {
        return mOrder.orderByAsc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orderByAsc(String[] orderBy) {
        return mOrder.orderByAsc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orderByDesc(String orderBy) {
        return mOrder.orderByDesc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Select orderByDesc(String[] orderBy) {
        return mOrder.orderByDesc(orderBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildStatement(boolean clear) {
        StringBuilder sql = new StringBuilder();
        String distinct = compile(mDistinct);
        String column = compile(mColumn);
        String from = compile(mFrom);
        String join = compile(mJoin);
        String where = compile(mWhere);
        String group = compile(mGroup);
        String having = compile(mHaving);
        String order = compile(mOrder);
        String limit = compile(mLimit);

        sql.append("SELECT");
        if (!distinct.isEmpty()) {
            sql.append(" ").append(distinct);
        }
        sql.append(" ").append(column.isEmpty() ? "*" : column);
        if (!from.isEmpty()) {
            sql.append("\nFROM ").append(from);
        }
        if (!join.isEmpty()) {
            sql.append("\n").append(join);
        }
        if (!where.isEmpty()) {
            sql.append("\nWHERE ").append(where);
        }
        if (!group.isEmpty()) {
            sql.append("\nGROUP BY ").append(group);
        }
        if (!having.isEmpty()) {
            sql.append("\nHAVING ").append(having);
        }
        if (!order.isEmpty()) {
            sql.append("\nORDER BY ").append(order);
        }
        if (!limit.isEmpty()) {
            sql.append("\n").append(limit);
        }
        return sql.toString();
    }

    @Override
    public void clearStatement() {
        clear(mColumn, mDistinct, mFrom, mJoin, mWhere, mGroup, mHaving, mOrder, mLimit);
    }

}
