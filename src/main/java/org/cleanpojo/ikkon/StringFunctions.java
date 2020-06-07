package org.cleanpojo.ikkon;

interface StringFunctions {

    static boolean startsWith(String s, String prefix) {
        return s.regionMatches(true, 0, prefix, 0, prefix.length());
    }
}
