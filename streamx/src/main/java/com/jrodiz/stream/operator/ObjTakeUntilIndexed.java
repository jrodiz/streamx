package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedPredicate;
import com.jrodiz.stream.iterator.IndexedIterator;
import com.jrodiz.stream.iterator.LsaExtIterator;
import org.jetbrains.annotations.NotNull;

public class ObjTakeUntilIndexed<T> extends LsaExtIterator<T> {

    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> stopPredicate;

    public ObjTakeUntilIndexed(
            @NotNull IndexedIterator<? extends T> iterator,
            @NotNull IndexedPredicate<? super T> predicate) {
        this.iterator = iterator;
        this.stopPredicate = predicate;
    }

    @Override
    protected void nextIteration() {
        hasNext = iterator.hasNext() && !(isInit && stopPredicate.test(iterator.getIndex(), next));
        if (hasNext) {
            next = iterator.next();
        }
    }
}
