package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.LongUnaryOperator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class LongIterate extends PrimitiveIterator.OfLong {

    private final LongUnaryOperator op;
    private long current;

    public LongIterate(long seed, @NotNull LongUnaryOperator f) {
        this.op = f;
        current = seed;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public long nextLong() {
        final long old = current;
        current = op.applyAsLong(current);
        return old;
    }
}
