package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedPredicate;
import com.jrodiz.stream.iterator.IndexedIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;

public class ObjFilterIndexed<T> implements Iterator<T> {

    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> predicate;
    private boolean hasNext, hasNextEvaluated;
    private T next;

    public ObjFilterIndexed(
            @NotNull IndexedIterator<? extends T> iterator,
            @NotNull IndexedPredicate<? super T> predicate) {
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
    public T next() {
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }
}
