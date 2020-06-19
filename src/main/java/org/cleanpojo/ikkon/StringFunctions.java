package org.cleanpojo.ikkon;

interface StringFunctions {

    static boolean startsWith(String s, String prefix) {
        boolean ignoreCase = true;
        return s.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
    }
}
