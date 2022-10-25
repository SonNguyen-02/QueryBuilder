package com.mct.database.query.ingredient.impl;

import com.mct.database.query.enums.LikeType;
import com.mct.database.query.ingredient.Condition;
import com.mct.database.query.ingredient.Where;
import com.mct.database.query.statement.BaseStatement;
import com.mct.database.query.utils.Utils;
import com.mct.database.query.utils.exec.BuilderError;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhereImpl<T extends BaseStatement> extends BaseIngredient<T> implements Condition, Where<T> {

    private final ArrayList<String> qbWhere = new ArrayList<>();

    private boolean qbWhereGroupStart;

    public WhereImpl(T statement) {
        super(statement);
    }

    @Override
    public String compile() {
        if (!qbWhere.isEmpty()) {
            String where = Utils.implode(qbWhere, " ");
            // remove brackets if redundant
            if (where.startsWith("(") && where.endsWith(")")) {
                where = where.substring(1, where.length() - 1).trim();
            }
            /*
             * Clean if condition have an empty group
             * eg: name = 'lol' AND ()   =>   name = 'lol'
             */
            if (where.matches(".*\\( *\\).*")) {
                String tmpRepl = "?";
                String regex = "'.*?[^\\\\]'";
                @RegExp
                String reg = "( *\\( *\\) +(AND|OR)?)|(((AND|OR)( NOT)?)? +\\( *\\) *)";
                String result = replaceAlls(where.replaceAll(regex, tmpRepl), reg);
                Matcher matcher = Pattern.compile(regex).matcher(where);
                while (matcher.find()) {
                    int find = result.indexOf(tmpRepl);
                    if (find != -1) {
                        result = result.substring(0, find) +
                                matcher.group() +
                                result.substring(find + 1);
                    }
                }
                return result;
            }
            return where;
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
        _groupEnd();
        return mStatement;
    }

    /**
     * {@link #_whv(String, Object)}
     */
    private T _wh(String key, Object value, String type) {
        qbWhere.add(_groupGetType(type) + _whv(key, value));
        return mStatement;
    }

    private T _wh(String where, String type) {
        qbWhere.add(_groupGetType(type) + _whv(where));
        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T where(String key, Object value) {
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
    public T orWhere(String key, Object value) {
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
     * {@link Where#whereIn(String, Object...)}<br/>
     * {@link Where#orWhereIn(String, Object...)}<br/>
     * {@link Where#whereNotIn(String, Object...)}<br/>
     * {@link Where#orWhereNotIn(String, Object...)}<br/>
     *
     * @param key    The field to search
     * @param values The values searched on
     * @param not    If the statement was IN or NOT IN
     * @param type   AND || OR
     * @return this
     */
    private T _whereIn(String key, Object[] values, boolean not, String type) {
        if (key == null || key.trim().isEmpty()) {
            throw new BuilderError("The key is invalid");
        }
        // empty -> skip this statement
        if (values != null && values.length != 0) {
            ArrayList<String> arrTmp = new ArrayList<>();
            for (Object value : values) {
                if (value != null) {
                    String s = value.toString().trim();
                    if (!s.isEmpty()) arrTmp.add(Utils.escape(s));
                }
            }
            // empty -> skip this statement
            if (!arrTmp.isEmpty()) {
                String prefix = _groupGetType(type);
                String notStr = not ? " NOT" : "";
                String in = Utils.implode(arrTmp.toArray(new String[0]), ", ");
                String whereIn = prefix + key.trim() + notStr + " IN (" + in + ")";
                this.qbWhere.add(whereIn);
            }
        }
        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T whereIn(String key, Object... value) {
        return _whereIn(key, value, false, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhereIn(String key, Object... value) {
        return _whereIn(key, value, false, "OR ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T whereNotIn(String key, Object... value) {
        return _whereIn(key, value, true, "AND ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhereNotIn(String key, Object... value) {
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
    private T _like(String field, String match, String type, LikeType likeType, String not) {
        if (field == null || field.trim().isEmpty()) {
            throw new BuilderError("The field is invalid");
        }
        field = field.trim();
        // empty -> skip this statement
        if (match != null && !match.trim().isEmpty()) {
            match = Utils.escapeLikeStr(match.trim());
            if (likeType == null) likeType = LikeType.BOTH;
            //noinspection EnhancedSwitchMigration
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
            String prefix = _groupGetType(type);
            String like = prefix + field + not + " LIKE " + match;
            qbWhere.add(like);
        }
        return mStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T like(String field, String match) {
        return _like(field, match, "AND ", null, "");
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
    public T orLike(String field, String match) {
        return _like(field, match, "OR ", null, "");
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
    public T notLike(String field, String match) {
        return _like(field, match, "AND ", null, " NOT");
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
    public T orNotLike(String field, String match) {
        return _like(field, match, "OR ", null, " NOT");
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
        if (qbWhereGroupStart || qbWhere.isEmpty()) {
            type = "";
        }
        qbWhereGroupStart = true;
        String prefix = type;
        String whereCondition = prefix + not + "(";
        qbWhere.add(whereCondition);
        return mStatement;
    }

    private void _groupEnd() {
        qbWhereGroupStart = false;
        qbWhere.add(")");
    }

    private String _groupGetType(String type) {
        if (qbWhereGroupStart) {
            type = "";
            qbWhereGroupStart = false;
        }
        return qbWhere.isEmpty() ? "" : type;
    }

    private @NotNull String replaceAlls(@NotNull String where, String regex) {
        String rpl;
        if (!(rpl = where.replaceAll(regex, "").trim()).equals(where)) {
            return replaceAlls(rpl, regex);
        }
        return where;
    }
}
