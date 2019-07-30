package com.jrodiz.stream.operator;

import com.jrodiz.stream.iterator.PrimitiveIterator;
import org.jetbrains.annotations.NotNull;

public class DoubleSkip extends PrimitiveIterator.OfDouble {

    private final PrimitiveIterator.OfDouble iterator;
    private final long n;
    private long skipped;

    public DoubleSkip(@NotNull PrimitiveIterator.OfDouble iterator, long n) {
        this.iterator = iterator;
        this.n = n;
        skipped = 0;
    }

    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            if (skipped == n) {
                break;
            }
            iterator.nextDouble();
            skipped++;
        }
        return iterator.hasNext();
    }

    @Override
    public double nextDouble() {
        return iterator.nextDouble();
    }
}
