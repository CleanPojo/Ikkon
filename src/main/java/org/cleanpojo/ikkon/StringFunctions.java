package org.cleanpojo.ikkon;

interface StringFunctions {

    static boolean startsWith(String s, String prefix) {
        boolean ignoreCase = true;
        return s.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
    }

    static boolean endsWith(String s, String suffix) {
        boolean ignoreCase = true;
        int offset = s.length() - suffix.length();
        return offset >= 0
            && s.regionMatches(ignoreCase, offset, suffix, 0, suffix.length());
    }
}
