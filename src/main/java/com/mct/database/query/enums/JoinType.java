package com.mct.database.query.enums;

import org.jetbrains.annotations.NotNull;

public enum JoinType {
    LEFT, RIGHT, OUTER, INNER, LEFT_OUTER, RIGHT_OUTER;

    @NotNull
    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}
