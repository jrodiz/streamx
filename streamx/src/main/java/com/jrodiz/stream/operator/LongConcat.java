package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class LongConcat extends PrimitiveIterator.OfLong {

    private final PrimitiveIterator.OfLong iterator1;
    private final PrimitiveIterator.OfLong iterator2;
    private boolean firstStreamIsCurrent;

    public LongConcat(
            @NotNull PrimitiveIterator.OfLong iterator1,
            @NotNull PrimitiveIterator.OfLong iterator2) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        firstStreamIsCurrent = true;
    }

    @Override
    public boolean hasNext() {
        if (firstStreamIsCurrent) {
            if (iterator1.hasNext()) {
                return true;
            }
            firstStreamIsCurrent = false;
        }
        return iterator2.hasNext();
    }

    @Override
    public long nextLong() {
        return firstStreamIsCurrent ? iterator1.nextLong() : iterator2.nextLong();
    }
}
