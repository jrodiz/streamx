package com.jrodiz.stream.function;

/**
 * Represents a function which produces an {@code double}-valued result from input argument.
 *
 * @since 1.1.4
 * @see Function
 */
public interface LongToDoubleFunction {

    /**
     * Applies this function to the given argument.
     *
     * @param value  an argument
     * @return the function result
     */
    double applyAsDouble(long value);
}
