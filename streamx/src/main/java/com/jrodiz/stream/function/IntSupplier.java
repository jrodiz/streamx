package com.jrodiz.stream.function;

import com.jrodiz.stream.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a supplier of {@code int}-valued results.  This is the
 * {@code int}-producing primitive specialization of {@link Supplier}.
 *
 * <p>There is no requirement that a distinct result be returned each
 * time the supplier is invoked.
 */
public interface IntSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     */
    int getAsInt();

    class Util {

        private Util() { }

        /**
         * Creates a safe {@code IntSupplier}.
         *
         * @param throwableSupplier  the supplier that may throw an exception
         * @return an {@code IntSupplier}
         * @throws NullPointerException if {@code throwableSupplier} is null
         * @since 1.1.7
         * @see #safe(com.jrodiz.stream.function.ThrowableIntSupplier, int)
         */
        public static IntSupplier safe(
                @NotNull ThrowableIntSupplier<Throwable> throwableSupplier) {
            return safe(throwableSupplier, 0);
        }

        /**
         * Creates a safe {@code IntSupplier}.
         *
         * @param throwableSupplier  the supplier that may throw an exception
         * @param resultIfFailed  the result which returned if exception was thrown
         * @return an {@code IntSupplier}
         * @throws NullPointerException if {@code throwableSupplier} is null
         * @since 1.1.7
         */
        public static IntSupplier safe(
                @NotNull final ThrowableIntSupplier<Throwable> throwableSupplier,
                final int resultIfFailed) {
            Objects.requireNonNull(throwableSupplier);
            return new IntSupplier() {

                @Override
                public int getAsInt() {
                    try {
                        return throwableSupplier.getAsInt();
                    } catch (Throwable ex) {
                        return resultIfFailed;
                    }
                }
            };
        }

    }
}
