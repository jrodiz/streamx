package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.LongConsumer;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class LongPeek extends PrimitiveIterator.OfLong {

    private final PrimitiveIterator.OfLong iterator;
    private final LongConsumer action;

    public LongPeek(
            @NotNull PrimitiveIterator.OfLong iterator,
            @NotNull LongConsumer action) {
        this.iterator = iterator;
        this.action = action;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public long nextLong() {
        final long value = iterator.nextLong();
        action.accept(value);
        return value;
    }
}
