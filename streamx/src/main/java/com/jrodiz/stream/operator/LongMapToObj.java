package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.LongFunction;
import com.jrodiz.stream.iterator.LsaIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class LongMapToObj<R> extends LsaIterator<R> {

    private final PrimitiveIterator.OfLong iterator;
    private final LongFunction<? extends R> mapper;

    public LongMapToObj(
            @NotNull PrimitiveIterator.OfLong iterator,
            @NotNull LongFunction<? extends R> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R nextIteration() {
        return mapper.apply(iterator.nextLong());
    }
}
