package org.cleanpojo.ikkon;

final class TryResult {

    private final Object value;
    private final Exception exception;

    private TryResult(final Object value) {
        this.value = value;
        this.exception = null;
    }

    private TryResult(final Exception exception) {
        this.value = null;
        this.exception = exception;
    }

    public static TryResult success(Object value) {
        return new TryResult(value);
    }

    public static TryResult failure(Exception exception) {
        return new TryResult(exception);
    }

    public Object getValue() {
        return value;
    }

    public Exception getException() {
        return exception;
    }
}
