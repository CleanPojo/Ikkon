package org.cleanpojo.ikkon;

final class DefaultValue {

    public static Object of(Class<?> type) {
        if (type.equals(byte.class)) {
            return (byte)0;
        } else if (type.equals(short.class)) {
            return (short)0;
        } else if (type.equals(int.class)) {
            return 0;
        } else if (type.equals(long.class)) {
            return (long)0;
        } else if (type.equals(float.class)) {
            return (float)0;
        } else if (type.equals(double.class)) {
            return (double)0;
        } else if (type.equals(boolean.class)) {
            return false;
        } else if (type.equals(char.class)) {
            return (char)0;
        } else {
            return null;
        }
    }
}
