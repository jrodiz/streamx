package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedLongUnaryOperator;
import com.jrodiz.stream.iterator.PrimitiveIndexedIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class LongMapIndexed extends PrimitiveIterator.OfLong {

    private final PrimitiveIndexedIterator.OfLong iterator;
    private final IndexedLongUnaryOperator mapper;

    public LongMapIndexed(
            @NotNull PrimitiveIndexedIterator.OfLong iterator,
            @NotNull IndexedLongUnaryOperator mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public long nextLong() {
        return mapper.applyAsLong(iterator.getIndex(), iterator.next());
    }
}
