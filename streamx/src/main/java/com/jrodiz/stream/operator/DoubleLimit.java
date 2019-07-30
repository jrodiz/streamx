package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class DoubleLimit extends PrimitiveIterator.OfDouble {

    private final PrimitiveIterator.OfDouble iterator;
    private final long maxSize;
    private long index;

    public DoubleLimit(@NotNull PrimitiveIterator.OfDouble iterator, long maxSize) {
        this.iterator = iterator;
        this.maxSize = maxSize;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return (index < maxSize) && iterator.hasNext();
    }

    @Override
    public double nextDouble() {
        index++;
        return iterator.nextDouble();
    }
}
