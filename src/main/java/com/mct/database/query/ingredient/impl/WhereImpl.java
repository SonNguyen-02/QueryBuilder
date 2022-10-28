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

    WhereImpl() {
    }

    @Override
    public String compile() {
        if (!qbWhere.isEmpty()) {
            String where = Utils.implode(qbWhere, " ");
            // remove brackets if redundant
            if (where.matches("^\\(.*\\)$")) {
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
                String reg = "( *\\( *\\)( *(AND|OR))?)|(((AND|OR)( NOT)? *)?\\( *\\) *)";
                String result = Utils.replaceAlls(where.replaceAll(regex, tmpRepl), reg);
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
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T where(@NotNull String condition, Object... value) {
        qbWhere.add(_groupGetType("AND ") + prepareCondition(condition, value));
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhere(@NotNull String condition, Object... value) {
        qbWhere.add(_groupGetType("OR ") + prepareCondition(condition, value));
        return getStatement();
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
     * @param type   AND || OR
     * @param not    If the statement was IN or NOT IN
     * @param key    The field to search
     * @param values The values searched on
     * @return this
     */
    private T _whereIn(String type, boolean not, String key, Object... values) {
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
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T whereIn(@NotNull String key, Object... value) {
        return _whereIn("AND ", false, key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhereIn(@NotNull String key, Object... value) {
        return _whereIn("OR ", false, key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T whereNotIn(@NotNull String key, Object... value) {
        return _whereIn("AND ", true, key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orWhereNotIn(@NotNull String key, Object... value) {
        return _whereIn("OR ", true, key, value);
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
     * @param type     AND | OR
     * @param not      NOT | ''
     * @param field    The column to search
     * @param match    The search value
     * @param likeType likeType
     * @return this
     */
    private T _like(String type, String not, String field, String match, LikeType likeType) {
        if (field == null || (field = field.trim()).isEmpty()) {
            throw new BuilderError("The field is invalid");
        }
        // empty -> skip this statement
        if (match != null && !(match = match.trim()).isEmpty()) {
            match = Utils.escapeLikeStr(match);
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
        return getStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T like(@NotNull String field, String match) {
        return like(field, match, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T like(@NotNull String field, String match, LikeType type) {
        return _like("AND ", "", field, match, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orLike(@NotNull String field, String match) {
        return orLike(field, match, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orLike(@NotNull String field, String match, LikeType likeType) {
        return _like("OR ", "", field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T notLike(@NotNull String field, String match) {
        return notLike(field, match, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T notLike(@NotNull String field, String match, LikeType likeType) {
        return _like("AND ", " NOT", field, match, likeType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orNotLike(@NotNull String field, String match) {
        return orNotLike(field, match, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T orNotLike(@NotNull String field, String match, LikeType likeType) {
        return _like("OR ", " NOT", field, match, likeType);
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
        return getStatement();
    }

    private void _groupEnd() {
        qbWhereGroupStart = false;
        qbWhere.add(")");
    }

    private String _groupGetType(String type) {
        if (qbWhereGroupStart) {
            qbWhereGroupStart = false;
            type = "";
        }
        return qbWhere.isEmpty() ? "" : type;
    }

}
