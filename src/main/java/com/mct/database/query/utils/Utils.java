package com.mct.database.query.utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Utils {

    public static String implode(String @NotNull [] arrayToImplode, String separator) {

        if (arrayToImplode.length == 0) { //empty array return empty string
            return "";
        }

        if (arrayToImplode.length < 2) { //only 1 item
            return arrayToImplode[0];
        }

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < arrayToImplode.length; i++) {
            if (i != 0) {
                strBuilder.append(separator);
            }
            strBuilder.append(arrayToImplode[i]);
        }

        //return the imploded string
        return strBuilder.toString();
    }

    public static String implode(@NotNull List<String> arrayToImplode, String separator) {
        return implode(arrayToImplode.toArray(new String[0]), separator);
    }

    @NotNull
    public static String replaceAlls(@NotNull String where, String regex) {
        String rpl;
        if (!(rpl = where.replaceAll(regex, "").trim()).equals(where)) {
            return replaceAlls(rpl, regex);
        }
        return where;
    }

    @NotNull
    public static String escape(@NotNull String str) {
        return "'" + escapeStr(str) + "'";
    }

    @NotNull
    public static String escapeLikeStr(String str) {
        return escapeStr(str).replaceAll("%", "\\%").replaceAll("_", "\\_");
    }

    @NotNull
    public static String escapeStr(@NotNull String str) {
        int len = str.length();
        StringBuilder outBuffer = new StringBuilder(len * 2);
        for (int x = 0; x < len; x++) {
            char aChar = str.charAt(x);
            switch (aChar) {
                case '\\':
                    outBuffer.append('\\').append('\\');
                    break;
                case '\t':
                    outBuffer.append('\\').append('t');
                    break;
                case '\n':
                    outBuffer.append('\\').append('n');
                    break;
                case '\r':
                    outBuffer.append('\\').append('r');
                    break;
                case '\f':
                    outBuffer.append('\\').append('f');
                    break;
                case '"':
                case '\'':
                    outBuffer.append('\\').append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >> 8) & 0xF));
                        outBuffer.append(toHex((aChar >> 4) & 0xF));
                        outBuffer.append(toHex(aChar & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }

    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    private static final char[] hexDigit = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
}
