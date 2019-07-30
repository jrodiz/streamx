package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.DoubleToIntFunction;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class DoubleMapToInt extends PrimitiveIterator.OfInt {

    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleToIntFunction mapper;

    public DoubleMapToInt(
            @NotNull PrimitiveIterator.OfDouble iterator,
            @NotNull DoubleToIntFunction mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public int nextInt() {
        return mapper.applyAsInt(iterator.nextDouble());
    }
}
