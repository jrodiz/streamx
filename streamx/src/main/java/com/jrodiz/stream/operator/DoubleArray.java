package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class DoubleArray extends PrimitiveIterator.OfDouble {

    private final double[] values;
    private int index;

    public DoubleArray(@NotNull double[] values) {
        this.values = values;
        index = 0;
    }

    @Override
    public double nextDouble() {
        return values[index++];
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }
}
