package com.jrodiz.stream.function;

import com.jrodiz.stream.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a function which produces result from {@code long}-valued input argument.
 *
 * @param <R> the type of the result of the function
 *
 * @since 1.1.4
 * @see Function
 */
public interface LongFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value  an argument
     * @return the function result
     */
    R apply(long value);

    class Util {

        private Util() { }

        /**
         * Creates a safe {@code LongFunction},
         *
         * @param <R> the type of the result of the function
         * @param throwableFunction  the function that may throw an exception
         * @return the function result or {@code null} if exception was thrown
         * @throws NullPointerException if {@code throwableFunction} is null
         * @since 1.1.7
         * @see #safe(com.jrodiz.stream.function.ThrowableLongFunction, java.lang.Object)
         */
        public static <R> LongFunction<R> safe(
                @NotNull ThrowableLongFunction<? extends R, Throwable> throwableFunction) {
            return Util.<R>safe(throwableFunction, null);
        }

        /**
         * Creates a safe {@code LongFunction},
         *
         * @param <R> the type of the result of the function
         * @param throwableFunction  the function that may throw an exception
         * @param resultIfFailed  the result which returned if exception was thrown
         * @return the function result or {@code resultIfFailed}
         * @throws NullPointerException if {@code throwableFunction} is null
         * @since 1.1.7
         */
        public static <R> LongFunction<R> safe(
                @NotNull final ThrowableLongFunction<? extends R, Throwable> throwableFunction,
                @Nullable final R resultIfFailed) {
            Objects.requireNonNull(throwableFunction);
            return new LongFunction<R>() {

                @Override
                public R apply(long value) {
                    try {
                        return throwableFunction.apply(value);
                    } catch (Throwable throwable) {
                        return resultIfFailed;
                    }
                }
            };
        }

    }
}