package com.mct.database.query.ingredient.impl;

import com.mct.database.query.enums.LikeType;
import com.mct.database.query.utils.exec.BuilderError;
import com.mct.database.query.ingredient.Condition;
import com.mct.database.query.ingredient.Where;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WhereImpl<T extends BaseStatement> extends BaseIngredient<T> implements Condition, Where<T> {

    private final ArrayList<String> qbWhere = new ArrayList<>();

    private boolean qbWhereGroupStart;

    public WhereImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        if (!qbWhere.isEmpty()) {
            return Utils.implode(qbWhere, " ");
        }
        return "";
    }

    @Override
    public void clear() {
        qbWhere.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T groupStart() {
        return _grStart("", "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orGroupStart() {
        return _grStart("", "OR ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T notGroupStart() {
        return _grStart("NOT ", "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orNotGroupStart() {
        return _grStart("NOT ", "OR ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T groupEnd() {
        qbWhereGroupStart = false;
        qbWhere.add(")");
        return mStatement;
    }

    /**
     * {@link #_whv(String, String)}
     */
    private T _wh(String key, String value, String type) {
        qbWhere.add((qbWhere.isEmpty() ? _groupGetType("") : _groupGetType(type)) + _whv(key, value));
        return mStatement;
    }

    private T _wh(String where, String type) {
        qbWhere.add((qbWhere.isEmpty() ? _groupGetType("") : _groupGetType(type)) + _whv(where));
        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T where(String key, String value) {
        return _wh(key, value, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T where(String where) {
        return _wh(where, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhere(String key, String value) {
        return _wh(key, value, "OR ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhere(String where) {
        return _wh(where, "OR ");
    }

    /**
     * <strong>_whereIn:</strong><br/>
     * <p/>
     * Called by<br/>
     * {@link Where#whereIn(String, String[])}<br/>
     * {@link Where#orWhereIn(String, String[])}<br/>
     * {@link Where#whereNotIn(String, String[])}<br/>
     * {@link Where#orWhereNotIn(String, String[])}<br/>
     *
     * @param key    The field to search
     * @param values The values searched on
     * @param not    If the statement was IN or NOT IN
     * @param type   AND || OR
     * @return this
     */
    private T _whereIn(String key, String[] values, boolean not, String type) {
        if (key == null || key.isEmpty()) {
            throw new BuilderError("Key is empty");
        }
        if (values == null || values.length == 0) {
            throw new BuilderError("Value is empty");
        }

        String prefix = this.qbWhere.isEmpty() ? _groupGetType("") : _groupGetType(type);
        String notStr = not ? " NOT" : "";
        ArrayList<String> arrTmp = new ArrayList<>();
        for (String value : values) {
            value = value.trim();
            if (!value.isEmpty()) {
                arrTmp.add(Utils.escape(value));
            }
        }

        String whereIn = prefix + key.trim() + notStr + " IN (" + Utils.implode(arrTmp.toArray(new String[0]), ", ") + ")";
        this.qbWhere.add(whereIn);

        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T whereIn(String key, String[] value) {
        return _whereIn(key, value, false, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhereIn(String key, String[] value) {
        return _whereIn(key, value, false, "OR ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T whereNotIn(String key, String[] value) {
        return _whereIn(key, value, true, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhereNotIn(String key, String[] value) {
        return _whereIn(key, value, true, "OR ");
    }

    /**
     * <strong>_like:</strong><br/>
     * <p/>
     * Called by<br/>
     * {@link Where#like(String, String, LikeType)}<br/>
     * {@link Where#orLike(String, String, LikeType)}<br/>
     * {@link Where#notLike(String, String, LikeType)}<br/>
     * {@link Where#orNotLike(String, String, LikeType)}<br/>
     *
     * @param field    The column to search
     * @param match    The search value
     * @param type     AND | OR
     * @param likeType likeType
     * @param not      NOT | ''
     * @return this
     */
    private T _like(String field, String match, String type, @NotNull LikeType likeType, String not) {
        String likeStatement;
        field = field.trim();
        match = Utils.escapeLikeStr(match.trim());
        String prefix = qbWhere.isEmpty() ? _groupGetType("") : _groupGetType(type);
        switch (likeType) {
            case NONE:
                match = "'" + match + "'";
                break;
            case BEFORE:
                match = "'%" + match + "'";
                break;
            case AFTER:
                match = "'" + match + "%'";
                break;
            case BOTH:
            default:
                match = "'%" + match + "%'";
                break;
        }
        likeStatement = prefix + field + not + " LIKE " + match;
        qbWhere.add(likeStatement);

        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T like(String field, String match, LikeType type) {
        return _like(field, match, "AND ", type, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orLike(String field, String match, LikeType likeType) {
        return _like(field, match, "OR ", likeType, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T notLike(String field, String match, LikeType likeType) {
        return _like(field, match, "AND ", likeType, " NOT");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orNotLike(String field, String match, LikeType likeType) {
        return _like(field, match, "OR ", likeType, " NOT");
    }

    /**
     * <strong>_grStart:</strong><br/>
     * Build query group for where condition
     *
     * @param not  not
     * @param type type
     * @return this
     */
    private T _grStart(String not, String type) {
        type = _groupGetType(type);
        qbWhereGroupStart = true;
        String prefix = qbWhere.isEmpty() ? "" : type;
        String whereCondition = prefix + not + "(";
        qbWhere.add(whereCondition);
        return mStatement;
    }

    private String _groupGetType(String type) {
        if (qbWhereGroupStart) {
            type = "";
            qbWhereGroupStart = false;
        }
        return type;
    }


}
