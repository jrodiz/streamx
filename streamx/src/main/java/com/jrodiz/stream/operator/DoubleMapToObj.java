package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.DoubleFunction;
import com.jrodiz.stream.iterator.LsaIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class DoubleMapToObj<R> extends LsaIterator<R> {

    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleFunction<? extends R> mapper;

    public DoubleMapToObj(
            @NotNull PrimitiveIterator.OfDouble iterator,
            @NotNull DoubleFunction<? extends R> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R nextIteration() {
        return mapper.apply(iterator.nextDouble());
    }
}
