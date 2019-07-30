package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.IndexedPredicate;
import com.jrodiz.stream.iterator.IndexedIterator;
import com.jrodiz.stream.iterator.LsaExtIterator;
import org.jetbrains.annotations.NotNull;

public class ObjDropWhileIndexed<T> extends LsaExtIterator<T> {

    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> predicate;

    public ObjDropWhileIndexed(
            @NotNull IndexedIterator<? extends T> iterator,
            @NotNull IndexedPredicate<? super T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    protected void nextIteration() {
        if (!isInit) {
            // Skip first time
            while (hasNext = iterator.hasNext()) {
                final int index = iterator.getIndex();
                next = iterator.next();
                if (!predicate.test(index, next)) {
                    return;
                }
            }
        }

        hasNext = hasNext && iterator.hasNext();
        if (!hasNext) return;

        next = iterator.next();
    }
}
