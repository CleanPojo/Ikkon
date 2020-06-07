package org.cleanpojo.ikkon;

final class GetResult {

    private final Object value;
    private final Exception exception;

    private GetResult(final Object value) {
        this.value = value;
        this.exception = null;
    }

    private GetResult(final Exception exception) {
        this.value = null;
        this.exception = exception;
    }

    public static GetResult success(Object value) {
        return new GetResult(value);
    }

    public static GetResult failure(Exception exception) {
        return new GetResult(exception);
    }

    public Object getValue() {
        return value;
    }

    public Exception getException() {
        return exception;
    }
}
