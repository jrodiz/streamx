package com.jrodiz.stream.operator;

import com.jrodiz.stream.function.BiFunction;
import com.jrodiz.stream.iterator.LsaExtIterator;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ObjScanIdentity<T, R> extends LsaExtIterator<R> {

    private final Iterator<? extends T> iterator;
    private final R identity;
    private final BiFunction<? super R, ? super T, ? extends R> accumulator;

    public ObjScanIdentity(
            @NotNull Iterator<? extends T> iterator,
            @Nullable R identity,
            @NotNull BiFunction<? super R, ? super T, ? extends R> accumulator) {
        this.iterator = iterator;
        this.identity = identity;
        this.accumulator = accumulator;
    }

    @Override
    protected void nextIteration() {
        if (!isInit) {
            // Return identity
            hasNext = true;
            next = identity;
            return;
        }
        hasNext = iterator.hasNext();
        if (hasNext) {
            final T t = iterator.next();
            next = accumulator.apply(next, t);
        }
    }
}
