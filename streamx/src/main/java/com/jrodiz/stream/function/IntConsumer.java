package com.jrodiz.stream.function;

import com.jrodiz.stream.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an operation that accepts a single {@code int}-valued argument and
 * returns no result.  This is the primitive type specialization of
 * {@link com.jrodiz.stream.function.Consumer} for {@code int}.  Unlike most other
 * functional interfaces, {@code IntConsumer} is expected to operate via side-effects.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(int)}
 *
 * @see com.jrodiz.stream.function.Consumer
 */
public interface IntConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(int value);

    class Util {

        private Util() { }

        /**
         * Composes {@code IntConsumer} calls.
         *
         * <p>{@code c1.accept(value); c2.accept(value); }
         *
         * @param c1  the first {@code IntConsumer}
         * @param c2  the second {@code IntConsumer}
         * @return a composed {@code IntConsumer}
         * @throws NullPointerException if {@code c1} or {@code c2} is null
         */
        public static IntConsumer andThen(
                @NotNull final IntConsumer c1,
                @NotNull final IntConsumer c2) {
            Objects.requireNonNull(c1, "c1");
            Objects.requireNonNull(c2, "c2");
            return new IntConsumer() {
                @Override
                public void accept(int value) {
                    c1.accept(value);
                    c2.accept(value);
                }
            };
        }

        /**
         * Creates a safe {@code IntConsumer}.
         *
         * @param throwableConsumer  the consumer that may throw an exception
         * @return an {@code IntConsumer}
         * @throws NullPointerException if {@code throwableConsumer} is null
         * @since 1.1.7
         * @see #safe(com.jrodiz.stream.function.ThrowableIntConsumer, com.jrodiz.stream.function.IntConsumer)
         */
        public static IntConsumer safe(@NotNull ThrowableIntConsumer<Throwable> throwableConsumer) {
            return safe(throwableConsumer, null);
        }

        /**
         * Creates a safe {@code IntConsumer}.
         *
         * @param throwableConsumer  the consumer that may throw an exception
         * @param onFailedConsumer  the consumer which applies if exception was thrown
         * @return an {@code IntConsumer}
         * @throws NullPointerException if {@code throwableConsumer} is null
         * @since 1.1.7
         * @see #safe(com.jrodiz.stream.function.ThrowableIntConsumer)
         */
        public static IntConsumer safe(
                @NotNull final ThrowableIntConsumer<Throwable> throwableConsumer,
                @Nullable final IntConsumer onFailedConsumer) {
            Objects.requireNonNull(throwableConsumer);
            return new IntConsumer() {

                @Override
                public void accept(int value) {
                    try {
                        throwableConsumer.accept(value);
                    } catch (Throwable ex) {
                        if (onFailedConsumer != null) {
                            onFailedConsumer.accept(value);
                        }
                    }
                }
            };
        }

    }
}
