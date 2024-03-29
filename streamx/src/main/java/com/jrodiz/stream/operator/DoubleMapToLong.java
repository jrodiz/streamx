package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.DoubleToLongFunction;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class DoubleMapToLong extends PrimitiveIterator.OfLong {

    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleToLongFunction mapper;

    public DoubleMapToLong(
            @NotNull PrimitiveIterator.OfDouble iterator,
            @NotNull DoubleToLongFunction mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public long nextLong() {
        return mapper.applyAsLong(iterator.nextDouble());
    }
}
