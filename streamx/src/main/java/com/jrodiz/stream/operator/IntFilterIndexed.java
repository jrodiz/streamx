package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedIntPredicate;
import com.jrodiz.stream.iterator.PrimitiveIndexedIterator;
import com.jrodiz.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;

public class IntFilterIndexed extends PrimitiveIterator.OfInt {

    private final PrimitiveIndexedIterator.OfInt iterator;
    private final IndexedIntPredicate predicate;
    private boolean hasNext, hasNextEvaluated;
    private int next;

    public IntFilterIndexed(
            @NotNull PrimitiveIndexedIterator.OfInt iterator,
            @NotNull IndexedIntPredicate predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        if (!hasNextEvaluated) {
            nextIteration();
            hasNextEvaluated = true;
        }
        return hasNext;
    }

    @Override
    public int nextInt() {
        if (!hasNextEvaluated) {
            hasNext = hasNext();
        }
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        hasNextEvaluated = false;
        return next;
    }

    private void nextIteration() {
        while (iterator.hasNext()) {
            final int index = iterator.getIndex();
            next = iterator.next();
            if (predicate.test(index, next)) {
                hasNext = true;
                return;
            }
        }
        hasNext = false;
    }
}
