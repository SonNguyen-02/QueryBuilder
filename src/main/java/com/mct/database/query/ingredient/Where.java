package com.mct.database.query.ingredient;

import com.mct.database.query.enums.LikeType;
import org.jetbrains.annotations.NotNull;

public interface Where<T> {

    /**
     * <strong>Group Start:</strong><br/>
     * -VD: <br/>
     * <pre>
     * groupStart()
     *     .where('name = ?', 'Tom')
     *     .orGroupStart()
     *         .where('age = ?', '10')
     *         .where('gender = ?', 'male')
     *     .group_end()
     * .group_end()
     * .like("email", "admin")
     * </pre>
     * -Produces: <br/>
     * ( name = 'Tom' OR ( age = '10' AND gender = 'male' ) ) AND email like('%admin%')
     *
     * @return this
     */
    T groupStart();

    /**
     * <strong>Or Group Start:</strong><br/>
     * -VD: <br/>
     * <pre>
     * groupStart()
     *     .where('name = ?', 'Tom')
     *     .orGroupStart()
     *         .where('age = ?', '10')
     *         .where('gender = ?', 'male')
     *     .group_end()
     * .group_end()
     * .like("email", "admin")
     * </pre>
     * -Produces: <br/>
     * ( name = 'Tom' OR ( age = '10' AND gender = 'male' ) ) AND email like('%admin%')
     *
     * @return this
     */
    T orGroupStart();

    /**
     * <strong>Not Group Start:</strong><br/>
     * -VD: <br/>
     * <pre>
     * groupStart()
     *     .where('name = ?', 'Tom')
     *     .orGroupStart()
     *         .where('age = ?', '10')
     *         .where('gender = ?', 'male')
     *     .group_end()
     * .group_end()
     * .like("email", "admin")
     * </pre>
     * -Produces: <br/>
     * ( name = 'Tom' OR ( age = '10' AND gender = 'male' ) ) AND email like('%admin%')
     *
     * @return this
     */
    T notGroupStart();

    /**
     * <strong>Or Not Group Start:</strong><br/>
     * -VD: <br/>
     * <pre>
     * groupStart()
     *     .where('name = ?', 'Tom')
     *     .orGroupStart()
     *         .where('age = ?', '10')
     *         .where('gender = ?', 'male')
     *     .group_end()
     * .group_end()
     * .like("email", "admin")
     * </pre>
     * -Produces: <br/>
     * ( name = 'Tom' OR ( age = '10' AND gender = 'male' ) ) AND email like('%admin%')
     *
     * @return this
     */
    T orNotGroupStart();

    /**
     * <strong>Group End:</strong><br/>
     * End of group.
     *
     * @return this
     */
    T groupEnd();

    /**
     * <strong>Where:</strong><br/>
     * Generates the WHERE condition of the query. Separates multiple calls with AND.<br/>
     * -VD:
     * <br/>&nbsp;&nbsp;where("id = ?", 1);
     * <br/>&nbsp;&nbsp;where("age != ?", 15);
     * <br/>&nbsp;&nbsp;where("age is null");
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp;WHERE id = '1';
     * <br/>&nbsp;&nbsp;AND age != '15';
     * <br/>&nbsp;&nbsp;AND age is null;
     * <br/>
     * <b>-Note:</b><br/>
     * Conditional sentences must not contain 2 question marks next to each other.<br/>
     * <span style="color: #ff4c18">eg: .where("id = ? ?", 1) => An error throw</span><p/>
     *
     * @param condition The field to search
     * @param value     The value searched on
     * @return this
     * <br/>see {@link Condition#prepareCondition(String, Object...)}
     */
    T where(@NotNull String condition, Object... value);

    /**
     * <strong>Or Where:</strong><br/>
     * Generates the WHERE condition of the query. Separates multiple calls with OR.<br/>
     * -VD:
     * <br/>&nbsp;&nbsp;orWhere("id = ?", 1);
     * <br/>&nbsp;&nbsp;orWhere("age != ?", 15);
     * <br/>&nbsp;&nbsp;orWhere("age is null");
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp;OR id = '1';
     * <br/>&nbsp;&nbsp;OR age != '15';
     * <br/>&nbsp;&nbsp;OR age is null;
     * <br/>
     * <b>-Note:</b><br/>
     * Conditional sentences must not contain 2 question marks next to each other.<br/>
     * <span style="color: #ff4c18">eg: .orWhere("id = ? ?", 1) => An error throw</span><p/>
     *
     * @param condition The field to search
     * @param value     The value searched on
     * @return this
     * <br/>see {@link Condition#prepareCondition(String, Object...)}
     */
    T orWhere(@NotNull String condition, Object... value);

    /**
     * <strong>Where In:</strong><br/>
     * Generates a WHERE field IN ('item', 'item') SQL query joined with AND if appropriate<br/>
     * -VD:
     * <br/>&nbsp;&nbsp;Object[] names = new Object[]{"Frank", "Todd", "James"};
     * <br/>&nbsp;&nbsp;where_in('username', names);
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp; WHERE username IN ('Frank', 'Todd', 'James')
     *
     * @param key   The field to search
     * @param value The values searched on
     * @return this
     */
    T whereIn(@NotNull String key, Object... value);

    /**
     * <strong>Or Where In:</strong><br/>
     * Generates a WHERE field IN ('item', 'item') SQL query joined with OR if appropriate<br/>
     * -VD:
     * <br/>&nbsp;&nbsp;Object[] names = new Object[]{"Frank", "Todd", "James"};
     * <br/>&nbsp;&nbsp;where_in('username', names);
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp; OR username IN ('Frank', 'Todd', 'James')
     *
     * @param key   The field to search
     * @param value The values searched on
     * @return this
     */
    T orWhereIn(@NotNull String key, Object... value);

    /**
     * <strong>Where Not In:</strong><br/>
     * Generates a WHERE field IN ('item', 'item') SQL query joined with AND if appropriate<br/>
     * -VD:
     * <br/>&nbsp;&nbsp;Object[] names = new Object[]{"Frank", "Todd", "James"};
     * <br/>&nbsp;&nbsp;where_in('username', names);
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp; WHERE username NOT IN ('Frank', 'Todd', 'James')
     *
     * @param key   The field to search
     * @param value The values searched on
     * @return this
     */
    T whereNotIn(@NotNull String key, Object... value);

    /**
     * <strong>Or Where Not In:</strong><br/>
     * Generates a WHERE field IN ('item', 'item') SQL query joined with OR if appropriate<br/>
     * -VD:
     * <br/>&nbsp;&nbsp;Object[] names = new Object[]{"Frank", "Todd", "James"};
     * <br/>&nbsp;&nbsp;where_in('username', names);
     * <br/>
     * -Produces:
     * <br/>&nbsp;&nbsp; OR username NOT IN ('Frank', 'Todd', 'James')
     *
     * @param key   The field to search
     * @param value The values searched on
     * @return this
     */
    T orWhereNotIn(@NotNull String key, Object... value);

    /**
     * <strong>Like:</strong><br/>
     * Generates a LIKE portion of the query. Separates multiple calls with AND
     *
     * @param field The column to search
     * @param match The search value
     * @return this
     */
    T like(@NotNull String field, String match);

    /**
     * <strong>Like:</strong><br/>
     * Generates a LIKE portion of the query. Separates multiple calls with AND
     *
     * @param field The column to search
     * @param match The search value
     * @param type  type
     * @return this
     */
    T like(@NotNull String field, String match, LikeType type);

    /**
     * <strong>Or Like:</strong><br/>
     * Generates a LIKE portion of the query. Separates multiple calls with OR
     *
     * @param field The column to search
     * @param match The search value
     * @return this
     */
    T orLike(@NotNull String field, String match);

    /**
     * <strong>Or Like:</strong><br/>
     * Generates a LIKE portion of the query. Separates multiple calls with OR
     *
     * @param field    The column to search
     * @param match    The search value
     * @param likeType likeType
     * @return this
     */
    T orLike(@NotNull String field, String match, LikeType likeType);

    /**
     * <strong>Not Like:</strong><br/>
     * Generates a NOT LIKE portion of the query. Separates multiple calls with AND
     *
     * @param field The column to search
     * @param match The search value
     * @return this
     */
    T notLike(@NotNull String field, String match);

    /**
     * <strong>Not Like:</strong><br/>
     * Generates a NOT LIKE portion of the query. Separates multiple calls with AND
     *
     * @param field    The column to search
     * @param match    The search value
     * @param likeType likeType
     * @return this
     */
    T notLike(@NotNull String field, String match, LikeType likeType);

    /**
     * <strong>Or Not Like:</strong><br/>
     * Generates a NOT LIKE portion of the query. Separates multiple calls with OR
     *
     * @param field The column to search
     * @param match The search value
     * @return this
     */
    T orNotLike(@NotNull String field, String match);

    /**
     * <strong>Or Not Like:</strong><br/>
     * Generates a NOT LIKE portion of the query. Separates multiple calls with OR
     *
     * @param field    The column to search
     * @param match    The search value
     * @param likeType likeType
     * @return this
     */
    T orNotLike(@NotNull String field, String match, LikeType likeType);
}
