package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedDoubleUnaryOperator;
import com.jrodiz.stream.iterator.PrimitiveIndexedIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class DoubleMapIndexed extends PrimitiveIterator.OfDouble {

    private final PrimitiveIndexedIterator.OfDouble iterator;
    private final IndexedDoubleUnaryOperator mapper;

    public DoubleMapIndexed(
            @NotNull PrimitiveIndexedIterator.OfDouble iterator,
            @NotNull IndexedDoubleUnaryOperator mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public double nextDouble() {
        return mapper.applyAsDouble(iterator.getIndex(), iterator.next());
    }
}
