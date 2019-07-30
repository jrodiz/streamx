package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class IntLimit extends PrimitiveIterator.OfInt {

    private final PrimitiveIterator.OfInt iterator;
    private final long maxSize;
    private long index;

    public IntLimit(@NotNull PrimitiveIterator.OfInt iterator, long maxSize) {
        this.iterator = iterator;
        this.maxSize = maxSize;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return (index < maxSize) && iterator.hasNext();
    }

    @Override
    public int nextInt() {
        index++;
        return iterator.nextInt();
    }
}
