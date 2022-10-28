package com.mct.database.query.ingredient;

import com.mct.database.query.utils.exec.BuilderError;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Condition {

    /**
     * <strong>prepareCondition:</strong> Combine condition params and<br/>
     * <p/>
     * Called by<br/>
     * {@link Having#having(String, Object...)}<br/>
     * {@link Having#orHaving(String, Object...)}<br/>
     * {@link Where#where(String, Object...)}<br/>
     * {@link Where#orWhere(String, Object...)}<br/>
     *
     * @param condition condition
     * @param value     value
     * @return this
     */
    @NotNull
    default String prepareCondition(String condition, Object... value) {
        if (condition == null || (condition = condition.trim()).isEmpty()) {
            throw new BuilderError("The condition is invalid");
        }
        if (condition.matches(".*\\? *\\?.*")) {
            throw new BuilderError("The condition param placeholder invalid!");
        }
        if (condition.startsWith("?") || condition.endsWith("?")) {
            condition = " " + condition + " ";
        }
        StringBuilder result = new StringBuilder();
        String reg = " +\\? +";
        int totalParameter = 0;
        Matcher matcher = Pattern.compile(reg).matcher(condition);
        while (matcher.find()) totalParameter++;

        String[] conditionSplit = condition.split(reg);
        for (int i = 0; i < conditionSplit.length; i++) {
            String val = "";
            if (i < totalParameter) {
                if (value != null && i < value.length && value[i] != null) {
                    if (!(val = value[i].toString().trim()).isEmpty()) {
                        val = Utils.escape(val);
                    }
                } else {
                    val = "** NOT SPECIFIED **";
                }
            }
            result.append(conditionSplit[i]).append(" ").append(val).append(" ");
        }
        return result.toString().trim();
    }

}
