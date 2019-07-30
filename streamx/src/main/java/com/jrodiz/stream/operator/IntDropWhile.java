package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IntPredicate;
import com.jrodiz.stream.iterator.PrimitiveExtIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class IntDropWhile extends PrimitiveExtIterator.OfInt {

    private final PrimitiveIterator.OfInt iterator;
    private final IntPredicate predicate;

    public IntDropWhile(
            @NotNull PrimitiveIterator.OfInt iterator,
            @NotNull IntPredicate predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    protected void nextIteration() {
        if (!isInit) {
            // Skip first time
            while (hasNext = iterator.hasNext()) {
                next = iterator.next();
                if (!predicate.test(next)) {
                    return;
                }
            }
        }

        hasNext = hasNext && iterator.hasNext();
        if (!hasNext) return;

        next = iterator.next();
    }
}
