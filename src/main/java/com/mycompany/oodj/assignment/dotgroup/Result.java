package com.mycompany.oodj.assignment.dotgroup;

//interface IResult {
//    public void  
//}

public abstract class Result<T, E> {

    // Returns true if the result is Ok, otherwise false (Err)
    public abstract boolean isOk();

    // Returns true if the result is Err, otherwise false (Ok)
    public abstract boolean isErr();

    // Retrieves the value if it's Ok, otherwise throws an exception
    public abstract T unwrap() throws IllegalStateException;

    // Retrieves the error if it's Err, otherwise throws an exception
    public abstract E unwrapErr() throws IllegalStateException;

    // Retrieves the value if Ok, or the default value provided if Err
    public abstract T unwrapOr(T defaultValue);

    // Factory methods to create Result instances
    public static <T, E> Result<T, E> ok(T value) {
        return new Ok<>(value);
    }

    public static <T, E> Result<T, E> err(E error) {
        return new Err<>(error);
    }
}

class Ok<T, E> extends Result<T, E> {
    private final T value;

    public Ok(T value) {
        this.value = value;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public E unwrapErr() {
        throw new IllegalStateException("Called unwrapErr on an Ok value");
    }

    @Override
    public T unwrapOr(T defaultValue) {
        return value;
    }
}

class Err<T, E> extends Result<T, E> {
    private final E error;

    public Err(E error) {
        this.error = error;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public T unwrap() {
        throw new IllegalStateException("Called unwrap on an Err value");
    }

    @Override
    public E unwrapErr() {
        return error;
    }

    @Override
    public T unwrapOr(T defaultValue) {
        return defaultValue;
    }
}
