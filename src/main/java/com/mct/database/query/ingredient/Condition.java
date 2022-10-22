package com.mct.database.query.ingredient;

import com.mct.database.query.utils.exec.BuilderError;
import com.mct.database.query.utils.Utils;
import org.jetbrains.annotations.NotNull;

public interface Condition {

    /**
     * <strong>_whv:</strong> Combine key value pair<br/>
     * <p/>
     * Called by<br/>
     * {@link Having#having(String, Object)}<br/>
     * {@link Having#orHaving(String, Object)}<br/>
     * {@link Where#where(String, Object)}<br/>
     * {@link Where#orWhere(String, Object)}<br/>
     *
     * @param key   key
     * @param value value
     * @return this
     */
    @NotNull
    default String _whv(String key, Object value) {
        if (key == null || (key = key.trim()).isEmpty()) {
            throw new BuilderError("The key is invalid");
        }
        String mVal = value == null ? "" : value.toString().trim();
        String keyLower = key.toLowerCase();

        if (_hasOperator(keyLower)) {
            if (!keyLower.matches("[\\w.]+\\s+(is null|is not null)")) {
                key += " ";
                mVal = Utils.escape(mVal);
            }
        } else {
            if (mVal.isEmpty()) {
                key += " IS NULL";
            } else {
                key += " = ";
                mVal = Utils.escape(mVal);
            }
        }
        return key + mVal;
    }

    /**
     * <strong>_whv:</strong><br/>
     * <p/>
     * Called by<br/>
     * {@link Having#having(String)}<br/>
     * {@link Having#orHaving(String)}<br/>
     * {@link Where#where(String)}<br/>
     * {@link Where#orWhere(String)}<br/>
     *
     * @param whereOrHaving whereOrHaving
     * @return this
     */
    default String _whv(String whereOrHaving) {
        if (whereOrHaving == null || whereOrHaving.trim().isEmpty()) {
            throw new BuilderError("Statement can't null or empty");
        }
        return whereOrHaving.trim();
    }

    static boolean _hasOperator(@NotNull String str) {
        String reg = "[\\w.]+\\s+(<|>|!=|=|>=|<=|is null|is not null)";
        return str.matches(reg);
    }

}
