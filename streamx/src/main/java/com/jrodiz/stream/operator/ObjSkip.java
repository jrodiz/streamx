package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.LsaIterator;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public class ObjSkip<T> extends LsaIterator<T> {

    private final Iterator<? extends T> iterator;
    private final long n;
    private long skipped;

    public ObjSkip(@NotNull Iterator<? extends T> iterator, long n) {
        this.iterator = iterator;
        this.n = n;
        skipped = 0;
    }

    @Override
    public boolean hasNext() {
        while (skipped < n) {
            if (!iterator.hasNext()) {
                return false;
            }
            iterator.next();
            skipped++;
        }
        return iterator.hasNext();
    }

    @Override
    public T nextIteration() {
        return iterator.next();
    }
}
