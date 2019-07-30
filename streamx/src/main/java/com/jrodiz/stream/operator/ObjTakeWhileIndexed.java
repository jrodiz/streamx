package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedPredicate;
import com.jrodiz.stream.iterator.IndexedIterator;
import com.jrodiz.stream.iterator.LsaExtIterator;
import org.jetbrains.annotations.NotNull;

public class ObjTakeWhileIndexed<T> extends LsaExtIterator<T> {

    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> predicate;

    public ObjTakeWhileIndexed(
            @NotNull IndexedIterator<? extends T> iterator,
            @NotNull IndexedPredicate<? super T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    protected void nextIteration() {
        hasNext = iterator.hasNext()
                && predicate.test(iterator.getIndex(), next = iterator.next());
    }
}
