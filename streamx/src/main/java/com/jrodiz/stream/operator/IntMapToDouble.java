package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IntToDoubleFunction;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class IntMapToDouble extends PrimitiveIterator.OfDouble {

    private final PrimitiveIterator.OfInt iterator;
    private final IntToDoubleFunction mapper;

    public IntMapToDouble(
            @NotNull PrimitiveIterator.OfInt iterator,
            @NotNull IntToDoubleFunction mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public double nextDouble() {
        return mapper.applyAsDouble(iterator.nextInt());
    }
}
