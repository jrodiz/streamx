package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class LongArray extends PrimitiveIterator.OfLong {

    private final long[] values;
    private int index;

    public LongArray(@NotNull long[] values) {
        this.values = values;
        index = 0;
    }

    @Override
    public long nextLong() {
        return values[index++];
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }
}
